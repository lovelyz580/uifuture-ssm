package com.uifuture.chapter12.dao;

import com.uifuture.chapter12.entity.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class BlogMapperTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlogMapperTest.class);

    @Test
    public void selectBlogsLike() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            Blog blog = new Blog();
            blog.setTitle("a");
            System.out.println("===================" + mapper.selectBlogsLike(blog));
        } finally {
            session.close();
        }
    }


    @Test
    public void selectBlogsTitleLike() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            System.out.println("===================" + mapper.selectBlogsTitleLike("a"));
        } finally {
            session.close();
        }
    }

}
