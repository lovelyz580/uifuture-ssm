/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.controller;

import com.uifuture.basics.form.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SpringMVC中常用注解的演示
 *
 * @author chenhx
 * @version AnnotationController.java, v 0.1 2018-08-05 下午 4:43
 */
@Controller
@RequestMapping("annotation")
public class AnnotationController {
    /**
     * 可以通过 http://localhost:8080/annotation/aaa/test 访问
     * 无法通过http://localhost:8080/annotation/test 访问
     *
     * @return
     */
    @RequestMapping("*/test")
    @ResponseBody
    public String test() {
        System.out.println("-----AnnotationController.test-----");
        return "test";
    }

    /**
     * 可以通过 http://localhost:8080/annotation/test2 访问
     * 和http://localhost:8080/annotation/aaa/test2 访问
     *
     * @return
     */
    @RequestMapping({"**/test2", "test3"})
    @ResponseBody
    public String test2() {
        System.out.println("-----AnnotationController.test2-----");
        return "test2";
    }

    /**
     * produces = MediaType.APPLICATION_JSON_UTF8_VALUE - 防止返回数据乱码
     * ResponseBody注解返回的默认编码为ISO-8859-1
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/testRequestBody"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public User testRequestBody(@RequestBody User user) {
        System.out.println("-----user=" + user);
        return user;
    }

    /**
     * 演示PathVariable注解
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/{str}")
    public @ResponseBody
    String testPathVariable(@PathVariable("str") String name) {
        return name;
    }
}