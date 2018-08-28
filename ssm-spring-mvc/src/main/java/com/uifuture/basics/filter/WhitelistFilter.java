/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 白名单过滤器
 *
 * @author chenhx
 * @version SensitiveWordFilter.java, v 0.1 2018-08-28 下午 10:38
 */
public class WhitelistFilter extends OncePerRequestFilter {

    private static Set<String> whitelistSet = new HashSet<>();

    /**
     * 实际项目中需要去服务器进行获取
     */
    static {
        whitelistSet.add("127.0.0.1");
    }

    /**
     * 获取IP
     * @param request
     * @return
     */
    public static String getIPByHttpServletRequest(HttpServletRequest request) {
        String ip = null;
        //通过请求头中的X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //P通过请求头中的roxy-Client-IP：apache 服务代理
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
     * 保证每个请求只会被调用一次
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //在Spring MVC的DispatcherServlet之前执行
        String ip = getIPByHttpServletRequest(request);
        if (whitelistSet.contains(ip)) {
            //放行
            filterChain.doFilter(request, response);
        }
    }

}