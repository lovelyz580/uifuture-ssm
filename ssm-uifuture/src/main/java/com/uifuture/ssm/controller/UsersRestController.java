/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.controller;

import com.uifuture.ssm.base.BaseController;
import com.uifuture.ssm.common.RedisConstants;
import com.uifuture.ssm.email.EmailConfig;
import com.uifuture.ssm.email.SendEmail;
import com.uifuture.ssm.email.impl.SendEmailCallable;
import com.uifuture.ssm.entity.UsersEntity;
import com.uifuture.ssm.enums.ResultCodeEnum;
import com.uifuture.ssm.redis.RedisClient;
import com.uifuture.ssm.req.UsersReq;
import com.uifuture.ssm.result.ResultModel;
import com.uifuture.ssm.service.UsersService;
import com.uifuture.ssm.util.PasswordUtils;
import com.uifuture.ssm.util.RegexUtils;
import com.uifuture.ssm.util.ValidateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenhx
 * @version UsersRestController.java, v 0.1 2019-09-16 13:55 chenhx
 */
@RequestMapping("/usersRest")
@Slf4j
@RestController
public class UsersRestController extends BaseController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private RedisClient redisClient;
    @Autowired
    private EmailConfig emailConfig;

    /**
     * 用户注册
     *
     * @return
     */
    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    public ResultModel registered(UsersReq usersReq, HttpServletRequest request) {
        //校验参数
        ValidateUtils.validate(usersReq);

        //用户名数字，字母判断，字母开头
        checkParam(usersReq.getUsername(), usersReq.getEmail());

        //校验用户名
        Integer num = usersService.selectCountByUsername(usersReq.getUsername());
        if (num > 0) {
            return ResultModel.fail(ResultCodeEnum.USERNAME_ALREADY_EXISTS);
        }
        //校验邮箱
        num = usersService.selectCountByEmail(usersReq.getEmail());
        if (num > 0) {
            return ResultModel.fail(ResultCodeEnum.EMAIL_ALREADY_EXISTS);
        }

        //同一个IP 10分钟内最多请求20次
        long times = redisClient.incr(RedisConstants.getRegTimesKey(getIpAddress(request)), RedisConstants.REG_MAX_TIME);
        if (times > RedisConstants.REG_MAX_TIMES) {
            //请求次数过多，稍后再试
            return ResultModel.fail(ResultCodeEnum.ALL_TOO_OFTEN);
        }
        //通过Redis确定验证码是否正确，获取验证码
        String realCode = redisClient.get(RedisConstants.getRegEmailKey(usersReq.getEmail())).getObject(String.class);
        if (realCode == null) {
            //验证码过期
            return ResultModel.fail(ResultCodeEnum.VERIFICATION_CODE_HAS_EXPIRED);
        }
        if (!realCode.equals(usersReq.getEmailCode())) {
            //验证码错误
            return ResultModel.fail(ResultCodeEnum.VERIFICATION_CODE_ERROR);
        }

        //构建用户数据，密码加密
        String salt = PasswordUtils.getSalt();
        String password = PasswordUtils.getPassword(usersReq.getPassword(), salt);
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUsername(usersReq.getUsername());
        usersEntity.setPassword(password);
        usersEntity.setSalt(salt);
        usersEntity.setEmail(usersReq.getEmail());
        usersEntity.setCreateId(0);
        usersEntity.setMailboxState(1);

        //数据入库
        usersService.save(usersEntity);

        return ResultModel.success();
    }


    /**
     * 发送邮箱验证码
     *
     * @return
     */
    @RequestMapping(value = "/sendEmailCode", method = RequestMethod.POST)
    public ResultModel sendEmailCode(String email, String username, HttpServletRequest request) {
        //校验参数
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(username)) {
            return ResultModel.fail(ResultCodeEnum.PARAMETER_ERROR);
        }
        //用户名数字，字母判断，字母开头
        checkParam(username, email);


        //校验用户名
        Integer num = usersService.selectCountByUsername(username);
        if (num > 0) {
            return ResultModel.fail(ResultCodeEnum.USERNAME_ALREADY_EXISTS);
        }
        //校验邮箱
        num = usersService.selectCountByEmail(email);
        if (num > 0) {
            return ResultModel.fail(ResultCodeEnum.EMAIL_ALREADY_EXISTS);
        }

        //判断是否已经发送，10分钟最多发送10次，同一IP

        //获取发送次数
        long times = redisClient.incr(RedisConstants.getSendEmailCodeTimesKey(getIpAddress(request)), RedisConstants.REG_MAX_TIME);
        if (times > RedisConstants.SEND_CODE_MAX_TIMES) {
            //请求次数过多，稍后再试
            return ResultModel.fail(ResultCodeEnum.ALL_TOO_OFTEN);
        }
        //判断是否已经发送过，激活码使用原来的
        String code;
        //获取原来的code
        String realCode = redisClient.get(RedisConstants.getRegEmailKey(email)).getObject(String.class);
        if (StringUtils.isNotEmpty(realCode)) {
            code = realCode;
        } else {
            code = PasswordUtils.randomNumberLower(6);
        }

        //发送邮件,用户进行激活
        SendEmail sendEmail = new SendEmail() {
            @Override
            public String getCode() {
                return code;
            }

            @Override
            public String getName() {
                return username;
            }

            @Override
            public String getEmail() {
                return email;
            }
        };
        //异步发送邮件
        SendEmailCallable sendEmailCallable = new SendEmailCallable(emailConfig, sendEmail);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(sendEmailCallable);

        //将code记录到Redis，时效为10分钟
        redisClient.set(RedisConstants.getRegEmailKey(email), code, RedisConstants.REG_MAX_TIME);

        return ResultModel.success();
    }


    /**
     * 获取用户名是否存在
     *
     * @return
     */
    @RequestMapping("/username")
    public ResultModel username(String username) {
        //校验参数
        if (StringUtils.isEmpty(username)) {
            return ResultModel.fail(ResultCodeEnum.PARAMETER_ERROR);
        }
        //通过返回的data，数字大于0，说明被使用了
        Integer num = usersService.selectCountByUsername(username);
        return ResultModel.success(num);
    }

    /**
     * 获取邮箱是否存在
     *
     * @return
     */
    @RequestMapping("/email")
    public ResultModel email(String email) {
        //校验参数
        if (StringUtils.isEmpty(email)) {
            return ResultModel.fail(ResultCodeEnum.PARAMETER_ERROR);
        }
        //通过返回的data，数字大于0，说明被使用了
        Integer num = usersService.selectCountByEmail(email);
        return ResultModel.success(num);
    }

    /**
     * 用户登录
     * 通过邮箱和用户名都可以登录，值都设置到用户名中
     *
     * @return
     */
    @RequestMapping("/login")
    public ResultModel login(UsersReq usersReq, HttpServletRequest request) {
        //校验参数
        if (StringUtils.isEmpty(usersReq.getPassword())) {
            return ResultModel.fail(ResultCodeEnum.PARAMETER_ERROR);
        }
        if (StringUtils.isEmpty(usersReq.getUsername())) {
            return ResultModel.fail(ResultCodeEnum.PARAMETER_ERROR);
        }
        UsersEntity usersEntity;
        //邮箱登录
        if (RegexUtils.checkEmail(usersReq.getUsername())) {
            usersEntity = usersService.selectByEmail(usersReq.getUsername());
        } else {
            usersEntity = usersService.selectByUsername(usersReq.getUsername());
        }
        if (usersEntity == null) {
            return ResultModel.fail(ResultCodeEnum.WRONG_PASSWORD_USERNAME_EMAIL);
        }
        String password = PasswordUtils.getPassword(usersReq.getPassword(), usersEntity.getSalt());
        if (!password.equals(usersEntity.getPassword())) {
            return ResultModel.fail(ResultCodeEnum.WRONG_PASSWORD_USERNAME_EMAIL);
        }
        //登录成功
        setLoginInfo(request, usersEntity);
        return ResultModel.success();
    }


}
