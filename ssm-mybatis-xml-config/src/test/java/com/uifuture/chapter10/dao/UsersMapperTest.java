package com.uifuture.chapter10.dao;

import com.uifuture.chapter11.dao.UsersMapper;
import com.uifuture.chapter11.entity.Users;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


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

    @Test
    public void insertList() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UsersMapper mapper = session.getMapper(UsersMapper.class);
            List<Users> usersList = new ArrayList<>();
            for (int i = 6; i < 7; i++) {
                Users users = new Users();
                users.setUsername("user" + i);
                users.setPassword("user" + i);
                users.setAge(i);
                usersList.add(users);
                System.out.println("==========" + users);
//                System.out.println("===================" + mapper.insert(users));
                System.out.println("==========" + users);
                System.out.println("----------");
                System.out.println("----------");
            }
            System.out.println("==========" + usersList);
            System.out.println("===================" + mapper.insertList(usersList));
            System.out.println("==========" + usersList);
        } finally {
            session.close();
        }
    }

    @Test
    public void selectUsersPrimaryKey() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UsersMapper mapper = session.getMapper(UsersMapper.class);
            System.out.println("===================" + mapper.selectUsersPrimaryKey(1));
        } finally {
            session.close();
        }
    }

    @Test
    public void selectUsers() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UsersMapper mapper = session.getMapper(UsersMapper.class);
            System.out.println("===================" + mapper.selectUsers(1));
        } finally {
            session.close();
        }
    }
}
