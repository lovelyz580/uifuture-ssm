package com.uifuture.chapter11.dao;

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
        try (SqlSession session = sqlSessionFactory.openSession();) {
            UsersMapper mapper = session.getMapper(UsersMapper.class);
            System.out.println("===================" + mapper.selectByPrimaryKey(1));
            //同一个sqlSession中走的是一级缓存
            System.out.println("===================" + mapper.selectByPrimaryKey(1));
        }
        //只有前面的SqlSession关闭了，新获取一个SqlSession，在开启二级缓存的情况下，才会走二级缓存
        try (SqlSession session = sqlSessionFactory.openSession();) {
            UsersMapper mapper = session.getMapper(UsersMapper.class);
            System.out.println("===================" + mapper.selectByPrimaryKey(1));
            System.out.println("===================" + mapper.selectByPrimaryKey(1));
        }
    }

    @Test
    public void insertList() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession();) {
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
        }
    }

    @Test
    public void selectUsersPrimaryKey() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession();) {
            UsersMapper mapper = session.getMapper(UsersMapper.class);
            System.out.println("===================" + mapper.selectUsersPrimaryKey(1));
        }
    }

    @Test
    public void selectUsers() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession();) {
            UsersMapper mapper = session.getMapper(UsersMapper.class);
            System.out.println("===================" + mapper.selectUsers(1));
        }
    }


    @Test
    public void selectuserResultMap() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession();) {
            UsersMapper mapper = session.getMapper(UsersMapper.class);
            System.out.println("===================" + mapper.selectuserResultMap(1));
        }
    }
}
