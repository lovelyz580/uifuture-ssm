/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.uifuture.ssm.service.UsersService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author chenhx
 * @version TestConfig.java, v 0.1 2018-07-01 下午 3:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml", "classpath*:spring-mybatis.xml"})
public class TestConfig {
    private Logger logger = Logger.getLogger(TestConfig.class);

    @Autowired
    private UsersService usersService;

    @Test
    public void testPass() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        DruidDataSource druidDataSource = (DruidDataSource) context.getBean("dataSource");
//        DriverManagerDataSource druidDataSource = (DriverManagerDataSource) context.getBean("dataSource");
        logger.debug("解密后的账号：" + druidDataSource.getUsername());
        logger.debug("解密后的密码：" + druidDataSource.getPassword());
    }

    @Test
    public void usersServiceGetOne() {
        logger.debug("获取的数据：" + usersService.getById(4));
    }
}
