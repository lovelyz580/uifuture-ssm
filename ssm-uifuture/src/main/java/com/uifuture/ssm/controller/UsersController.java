package com.uifuture.ssm.controller;


import com.uifuture.ssm.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /**
     * 用户注册
     *
     * @return
     */
    @RequestMapping("registered")
    @ResponseBody
    public ModelAndView index() {
        log.info("访问testController/index");
        return new ModelAndView("index");
    }


}
