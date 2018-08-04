/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Web配置
 *
 * @author chenhx
 * @version WebInitializer.java, v 0.1 2018-08-04 下午 7:08
 */
public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(MvcConfig.class);
        /**
         * 新建WebApplicationContext,注册配置类，并将其和当前servletContext关联
         */
        context.setServletContext(servletContext);

        //注册并映射调度程序servlet
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        /**
         * 注册Spring MVC的DispatcherServlet
         */
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}