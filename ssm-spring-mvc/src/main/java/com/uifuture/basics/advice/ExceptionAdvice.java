/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常类
 *
 * @author chenhx
 * @version ExceptionAdvice.java, v 0.1 2018-08-07 下午 9:47
 */
//@ControllerAdvice
public class ExceptionAdvice {

    private Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
    /**
     * 控制所有控制器方法的Model返回值
     *
     * @param model
     */
    @ModelAttribute
    public void modelAttribute(Model model) {
        logger.info("把返回值放入Model");
        model.addAttribute("globalName", "SpringMVC");
    }

    /**
     * 进行所有控制器的初始化数据转换，绑定
     *  在传递参数的时候就好先执行这个方法
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        logger.info("执行初始化数据绑定器");
    }

    /**
     * 设置需要捕获的异常为Exception异常，也就是所有的异常
     * 这里是返回视图，也可以返回JSON等数据，可以当成Controller进行处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView handException(Exception e) {
        e.printStackTrace();
        ModelAndView modelAndView = new ModelAndView("error/error");
        Map map = new HashMap(4);
        map.put("code", 404);
        map.put("message", e.getMessage());
        modelAndView.addAllObjects(map);
        return modelAndView;
    }
}