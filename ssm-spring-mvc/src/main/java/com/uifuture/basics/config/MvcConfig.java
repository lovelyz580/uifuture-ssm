/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenhx
 * @version MvcConfig.java, v 0.1 2018-08-04 下午 7:05
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.uifuture.basics")
public class MvcConfig {
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
     * 进行json解析 JSON转换器
     * 不进行写也是可以进行自动转换的。如果有其他的定制需求，比如日期转换等等，可以使用到该方法。
     * @return
     */
    @Bean
    public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        //设置数据格式
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.ALL);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
        return mappingJackson2HttpMessageConverter;
    }
}