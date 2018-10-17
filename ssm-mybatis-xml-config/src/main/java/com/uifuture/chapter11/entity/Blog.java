package com.uifuture.chapter11.entity;

import com.uifuture.chapter11.base.BaseEntity;

/**
 * table name: blog
 *
 * @author chenhaoxiang 2018-10-17
 */
public class Blog extends BaseEntity {
    /**
     * fields name: blog.title
     */
    private String title;

    /**
     * 作者id
     * fields name: blog.author_id
     */
    private Integer authorId;

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 作者id
     *
     * @return author_id 作者id
     */
    public Integer getAuthorId() {
        return authorId;
    }

    /**
     * 作者id
     *
     * @param authorId 作者id
     */
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Blog{");
        sb.append(super.toString());
        sb.append(",");
        sb.append("title='").append(title).append('\'');
        sb.append(", authorId=").append(authorId);
        sb.append('}');
        return sb.toString();
    }
}