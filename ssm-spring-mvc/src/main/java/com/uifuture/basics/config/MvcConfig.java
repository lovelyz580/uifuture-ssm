/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.config;

import com.uifuture.basics.exception.ExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.GzipResourceResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.theme.SessionThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

/**
 * ComponentScan - 启用Controller Bean的自动检测
 * @author chenhx
 * @version MvcConfig.java, v 0.1 2018-08-04 下午 7:05
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.uifuture.basics")
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 配置异常处理器链
     *
     * @param resolvers
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        //将ExceptionResolver添加到异常处理器集合中
        resolvers.add(new ExceptionResolver());
    }

    /**
     * 配置拦截器
     * 这里只是配置主题切换拦截器，可以使用addInterceptor方法配置多个拦截器
     *
     * @return
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ThemeChangeInterceptor themeChangeInterceptor = new ThemeChangeInterceptor();
        //设置主题名的参数名称为themeName，默认的名称为theme。这样可以在URL上传递一个名称为themeName的参数来指定使用的主题名称
        themeChangeInterceptor.setParamName("themeName");
        //addPathPatterns指定拦截路径，未指定的话拦截所有。excludePathPatterns方法指定不拦截路径
        registry.addInterceptor(themeChangeInterceptor).addPathPatterns("/theme");
    }

    /**
     * 配置主题解析器
     * 主题解析器只能配置一个
     * 当未配置主题解析器时，使用默认主题解析器（FixedThemeResolver）的默认主题
     * 注意，方法名称就是Bean的名称
     *
     * @return
     */
    @Bean
    public SessionThemeResolver themeResolver() {
        SessionThemeResolver sessionThemeResolver = new SessionThemeResolver();
        //配置默认的theme名称.在不进行指定默认的主题时，默认的名称为”theme”。在这里会进行查找WEB-INF/classes/theme/default.properties主题文件
        sessionThemeResolver.setDefaultThemeName("default");
        return sessionThemeResolver;
    }

    /**
     * 加载主题资源
     * 配置主题资源路径
     *
     * @return
     */
    @Bean
    public ResourceBundleThemeSource themeSource() {
        ResourceBundleThemeSource resourceBundleThemeSource = new ResourceBundleThemeSource();
        //配置主题名的前缀路径,注意最后的"/"不能少，因为会拼接主题名称的
        resourceBundleThemeSource.setBasenamePrefix("theme.");
        return resourceBundleThemeSource;
    }


    /**
     * 文件上传解析器
     *
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        //文件上传大小限制为50MB
        commonsMultipartResolver.setMaxUploadSize(1024 * 1024 * 50);
        //设置编码为utf-8
        commonsMultipartResolver.setDefaultEncoding("UIF-8");
        return commonsMultipartResolver;
    }

    /**
     * 注册JSP页面视图解析器
     *
     * @return
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        /**
         * 映射路径-运行时代码会将我们的页面自动编译到/WEB-INF/classes/views/下
         */
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        /**
         * 实际页面后缀
         */
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    /**
     * 使用编程式代码配置静态页面的映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置url中的路径
        registry.addResourceHandler("/static/**")
                //配置静态文件的路径,最后需要"/"结尾，否则会访问不到静态资源
                .addResourceLocations("classpath:/static/")
                //设置静态资源的缓存时间，单位为S
                .setCachePeriod(60 * 60 *24*365)
                .resourceChain(true)
                .addResolver(new GzipResourceResolver())
                //如果是资源处理链，则将默认的PathResourceResolver路径资源解析器放到最后（Spring推荐）
                .addResolver(new PathResourceResolver())
        ;
    }

    /**
     * 配置直接映射
     * 定义ParameterizableViewController的简单方式、
     * 当该控制器被访问的时候没有任何操作，只是进行相应的请求映射，则可以使用该方法
     * 章节:7.2.1
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //添加访问路径
        registry.addViewController("/cacheController/index")
                //设置视图名称 - 对应文件路径
                .setViewName("/cache/index");
        //映射文件上传的页面
        registry.addViewController("/upload/index").setViewName("/upload/index");
        //映射下载页面
        registry.addViewController("/download").setViewName("/download");
        //映射到主题切换演示页面
        registry.addViewController("/theme").setViewName("/theme");
    }
}