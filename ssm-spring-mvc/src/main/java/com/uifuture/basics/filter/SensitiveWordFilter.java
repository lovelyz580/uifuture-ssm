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
        //过滤request中的敏感词，用*号表示
        SensitiveWord req = new SensitiveWord((HttpServletRequest) request);
        //放行
        filterChain.doFilter(req, response);
    }

    /**
     * 使用装饰模式，将原装的HttpServletRequest进行功能增强
     */
    class SensitiveWord extends HttpServletRequestWrapper {
        public SensitiveWord(HttpServletRequest request) {
            super(request);
        }

        @Override
        public String getParameter(String name) {
            String str = super.getParameter(name);
            //敏感词肯定是从数据库导入或者文本中导入的，在这里仅仅进行模拟
            List<String> list = Arrays.asList("骂人", "过滤");
            for (String word : list) {
                str = str.replaceAll(word, "*");
            }
            return str;
        }

    }
}