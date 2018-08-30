/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 敏感词过滤器
 *
 * @author chenhx
 * @version SensitiveWordFilter.java, v 0.1 2018-08-28 下午 10:38
 */
public class SensitiveWordFilter extends OncePerRequestFilter {
    /**
     * 分隔符，建议可以弄复杂一点
     */
    private final static String SEPARATOR = "#-#=#&";
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
        //在过滤器中用装饰模式把 原装request的功能增强了
        //---拦截后台调用的getParamter()方法
        SensitiveWordRequest req = new SensitiveWordRequest(request);
        //放行
        filterChain.doFilter(req, response);
    }

    class SensitiveWordRequest extends HttpServletRequestWrapper {
        public SensitiveWordRequest(HttpServletRequest request) {
            super(request);
        }

        @Override
        public String getParameter(String name) {
            String str = super.getParameter(name);
            List<String> list = Arrays.asList("骂人", "敏感词");
            for (String word : list) {
                str = str.replaceAll(word, "*");
            }
            return str;
        }

        @Override
        public String[] getParameterValues(String name) {
            String[] strs = super.getParameterValues(name);
            List<String> list = Arrays.asList("骂人", "敏感词");
            StringBuffer allStrs = new StringBuffer(strs[0]);
            for (int i = 1; i < strs.length; i++) {
                allStrs.append(SEPARATOR + strs[i]);
            }
            for (String word : list) {
                allStrs = new StringBuffer(allStrs.toString().replaceAll(word, "*"));
            }
            return allStrs.toString().split(SEPARATOR);
        }
    }
}