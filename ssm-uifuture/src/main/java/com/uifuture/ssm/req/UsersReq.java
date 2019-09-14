/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户注册实体
 *
 * @author chenhx
 * @version UsersReq.java, v 0.1 2019-09-14 11:34 chenhx
 */
@Data
public class UsersReq implements Serializable {
    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    private String username;
    /**
     * 密码(使用MD5+盐加密)
     */
    @NotEmpty(message = "密码不能为空")
    private String password;
    /**
     * 邮箱
     */
    @NotEmpty(message = "邮箱不能为空")
    private String email;
}
