package com.uifuture.ssm.controller;


import com.uifuture.ssm.base.BaseController;
import com.uifuture.ssm.common.RedisConstants;
import com.uifuture.ssm.entity.UsersEntity;
import com.uifuture.ssm.enums.ResultCodeEnum;
import com.uifuture.ssm.redis.RedisClient;
import com.uifuture.ssm.req.UsersReq;
import com.uifuture.ssm.result.ResultModel;
import com.uifuture.ssm.service.UsersService;
import com.uifuture.ssm.util.ValidateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author chenhx
 * @since 2019-09-12
 */
@RequestMapping("/users")
@Slf4j
@Controller
public class UsersController extends BaseController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private RedisClient redisClient;
    /**
     * 用户注册
     *
     * @return
     */
    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    @ResponseBody
    public ResultModel registered(UsersReq usersReq, HttpServletRequest request) {
        //校验参数
        ValidateUtils.validate(usersReq);

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

        //TODO 数据入库

        return ResultModel.success();
    }


    /**
     * 发送邮箱验证码
     *
     * @return
     */
    @RequestMapping(value = "/sendEmailCode", method = RequestMethod.POST)
    @ResponseBody
    public ResultModel sendEmailCode(String email, HttpServletRequest request) {
        //校验参数
        if (StringUtils.isEmpty(email)) {
            return ResultModel.fail(ResultCodeEnum.PARAMETER_ERROR);
        }
        //校验邮箱没有注册
        UsersEntity usersEntity = usersService.selectByEmail(email);
        if (usersEntity != null) {
            return ResultModel.fail(ResultCodeEnum.EMAIL_ALREADY_EXISTS);
        }
        //判断是否已经发送，10分钟最多发送10次，同一IP

        //获取发送次数
        long times = redisClient.incr(RedisConstants.getSendEmailCodeTimesKey(getIpAddress(request)), RedisConstants.REG_MAX_TIME);
        if (times > RedisConstants.SEND_CODE_MAX_TIMES) {
            //请求次数过多，稍后再试
            return ResultModel.fail(ResultCodeEnum.ALL_TOO_OFTEN);
        }


        //异步发送邮件

        //将code记录到Redis，时效为10分钟
        return ResultModel.success();
    }


    /**
     * 获取用户名是否存在
     *
     * @return
     */
    @RequestMapping("/username")
    @ResponseBody
    public ResultModel username(String username) {

        redisClient.set("aaaaa", "中文测试", 100);
        log.info("结果：{}", redisClient.get("aaaaa"));
        //校验参数
        if (StringUtils.isEmpty(username)) {
            return ResultModel.fail(ResultCodeEnum.PARAMETER_ERROR);
        }
        Integer num = usersService.selectCountByUsername(username);
        return ResultModel.success(num);
    }

    /**
     * 获取邮箱是否存在
     *
     * @return
     */
    @RequestMapping("/email")
    @ResponseBody
    public ResultModel email(String email) {
        //校验参数
        if (StringUtils.isEmpty(email)) {
            return ResultModel.fail(ResultCodeEnum.PARAMETER_ERROR);
        }
        Integer num = usersService.selectCountByEmail(email);
        return ResultModel.success(num);
    }


}
