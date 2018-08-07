/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.basics.config;

/**
 * @author chenhx
 * @version MvcConfig.java, v 0.1 2018-08-04 下午 7:05
 */
//@Configuration
//@EnableWebMvc
//@ComponentScan("com.uifuture.basics")
//public class MvcConfig implements WebMvcConfigurer {
//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        /**
//         * 映射路径-运行时代码会将我们的页面自动编译到/WEB-INF/classes/views/下
//         */
//        viewResolver.setPrefix("/WEB-INF/classes/views/");
//        /**
//         * 实际页面后缀
//         */
//        viewResolver.setSuffix(".jsp");
//        viewResolver.setViewClass(JstlView.class);
//        return viewResolver;
//    }
//
//    /**
//     * 使用编程式代码配置静态页面的映射
//     * @param registry
//     */
////    @Override
////    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        //配置url中的路径
////        registry.addResourceHandler("/static/**")
////                //配置静态文件的路径
////                .addResourceLocations("classpath:/static/");
////    }
//
//}