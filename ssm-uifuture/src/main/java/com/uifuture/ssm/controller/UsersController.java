package com.uifuture.ssm.controller;


import com.uifuture.ssm.base.BaseController;
import com.uifuture.ssm.enums.ResultCodeEnum;
import com.uifuture.ssm.req.UsersReq;
import com.uifuture.ssm.result.ResultModel;
import com.uifuture.ssm.service.UsersService;
import com.uifuture.ssm.util.ValidateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    /**
     * 用户注册
     *
     * @return
     */
    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    @ResponseBody
    public ResultModel registered(UsersReq usersReq) {
        //校验参数
        ValidateUtils.validate(usersReq);
        //校验用户名
        Integer num = usersService.selectCountByUsername(usersReq.getUsername());
        if (num > 0) {
            return ResultModel.fail(ResultCodeEnum.USERNAME_ALREADY_EXISTS);
        }
        //校验邮箱


        return ResultModel.success();
    }


    /**
     * 获取用户名是否存在
     *
     * @return
     */
    @RequestMapping("/username")
    @ResponseBody
    public ModelAndView username(String username) {
        //校验用户名

        return new ModelAndView("index");
    }


}
