/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.enums;

/**
 * 响应码枚举
 *
 * @author chenhx
 * @version ResultCodeEnum.java, v 0.1 2019-09-14 11:40 chenhx
 */
public enum ResultCodeEnum {
    /**
     * 成功
     */
    SUCCESS("SUCCESS", 200),
    /**
     * 失败
     */
    FAIL("FAIL", 9999),
    /**
     * 未认证（签名错误）
     */
    UNAUTHORIZED("UNAUTHORIZED", 9998),
    /**
     * 接口不存在
     */
    NOT_FOUND("NOT_FOUND", 404),
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", 500),
    /**
     * 用户未登录
     */
    USER_NOT_LOGGED("请先登录", 9997),
    /**
     * 参数错误
     */
    PARAMETER_ERROR("参数错误", 9996),
    /**
     * 过于频繁
     */
    ALL_TOO_OFTEN("过于频繁", 9995),
    STATUS_EXCEPTION("状态异常", 9994),
    NO_PRIVILEGE("无权限", 9993),
    USERNAME_ALREADY_EXISTS("用户名已存在", 9992),
    ;

    private String name;
    private Integer value;

    ResultCodeEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static ResultCodeEnum getByValue(Integer value) {
        ResultCodeEnum[] valueList = ResultCodeEnum.values();
        for (ResultCodeEnum v : valueList) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        return null;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for property <tt>value</tt>.
     *
     * @return property value of value
     */
    public Integer getValue() {
        return value;
    }
}
