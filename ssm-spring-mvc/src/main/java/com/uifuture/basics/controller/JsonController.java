/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.controller;

import com.uifuture.basics.form.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * JSON数据交互演示
 *
 * @author chenhx
 * @version JsonController.java, v 0.1 2018-09-01 下午 4:29
 */
@Controller
@RequestMapping("/json")
public class JsonController {
    /**
     * JSON字符串转换为对象
     * 并且将对象转换成json格式数据返回到前端
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/user")
    @ResponseBody
    public User user(@RequestBody User user) {
        System.out.println("-----user=" + user);
        return user;
    }
}