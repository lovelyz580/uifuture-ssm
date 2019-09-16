/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.base;

import com.uifuture.ssm.common.UsersConstants;
import com.uifuture.ssm.entity.UsersEntity;
import com.uifuture.ssm.enums.ResultCodeEnum;
import com.uifuture.ssm.exception.CommonException;
import com.uifuture.ssm.util.RegexUtils;
import com.uifuture.ssm.util.SessionUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenhx
 * @version BaseController.java, v 0.1 2019-09-12 11:31 chenhx
 */
public class BaseController {


    /**
     * 获取IP
     *
     * @param request
     * @return
     */
    protected static String getIpAddress(HttpServletRequest request) {
        String ip = null;
        //通过请求头中的X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //通过请求头中的roxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //通过请求头中的WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //通过请求头中的HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //通过请求头中的X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }
        //某些网络通过多层代理，可能获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }
        //如果还不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 校验用户名和邮箱
     *
     * @param username
     * @param email
     */
    protected static void checkParam(String username, String email) {
        if (!RegexUtils.checkEmail(email)) {
            throw new CommonException(ResultCodeEnum.INCORRECT_MAILBOX_FORMAT);
        }
        if (!RegexUtils.checkUsername(username)) {
            throw new CommonException(ResultCodeEnum.PARAMETER_ERROR);
        }
    }

    /**
     * 获取用户登录信息
     *
     * @param request
     * @return
     */
    protected static UsersEntity getLoginInfo(HttpServletRequest request) {
        return SessionUtils.getAttribute(request, UsersConstants.SESSION_USERS_LOGIN_INFO);
    }

    /**
     * 设置用户登录信息
     *
     * @param request
     * @return
     */
    protected static void setLoginInfo(HttpServletRequest request, UsersEntity usersEntity) {
        SessionUtils.setAttribute(request, UsersConstants.SESSION_USERS_LOGIN_INFO, usersEntity);
    }

}
