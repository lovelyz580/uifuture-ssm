package com.uifuture.ssm.spring.ioc.demo14_7.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringIoCServiceTest {

    @Test
    public void testByXml() throws Exception {
        //加载配置文件
//        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-ioc.xml");
//        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring-ioc.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:spring-ioc*.xml");

        SpringIoCService springIoCService = applicationContext.getBean("springIoCServiceImpl", SpringIoCService.class);
        //多次获取并不会创建多个springIoCService对象,因为spring默认创建是单实例的作用域
//        SpringIoCService springIoCService= (SpringIoCService) applicationContext.getBean("springIoCServiceImpl");
        springIoCService.saySpringIoC();

    }

    @Test
    public void testBySystemXml() {
        //默认为项目工作路径 即项目的根目录
//        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("/src/main/resources/spring-ioc.xml");
        //也可以读取classpath下的文件
//        ApplicationContext applicationContext=new FileSystemXmlApplicationContext("classpath:spring-ioc.xml");
        //使用前缀file 表示的是文件的绝对路径
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("file:/Users/chenhx/Desktop/github/uifuture-ssm/ssm-spring-ioc-demo/src/main/resources/spring-ioc.xml");
        //多文件与ClassPathXmlApplicationContext相同
        SpringIoCService springIoCService = applicationContext.getBean("springIoCServiceImpl", SpringIoCService.class);
        springIoCService.saySpringIoC();

    }

}

