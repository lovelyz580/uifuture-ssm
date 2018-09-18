package com.uifuture.chapter10.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;


public class UsersMapperTest {

    @Test
    public void selectByPrimaryKey() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UsersMapper mapper = session.getMapper(UsersMapper.class);
            System.out.println("===================" + mapper.selectByPrimaryKey(1));
        } finally {
            session.close();
        }
    }
}
