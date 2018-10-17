package com.uifuture.chapter11.dao;


import com.uifuture.chapter11.entity.Blog;
import com.uifuture.chapter11.entity.BlogExt;
import com.uifuture.chapter11.entity.Post;

public interface BlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    /**
     * 通过博客id查询博客
     *
     * @param id
     * @return
     */
    BlogExt selectBlogExtById(Integer id);

    /**
     * 通过博客id查询文章
     *
     * @param id
     * @return
     */
    Post selectPostsForBlog(Integer id);

    BlogExt selectBlogExt(Integer id);
}