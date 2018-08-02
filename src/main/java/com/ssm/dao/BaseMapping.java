package com.ssm.dao;

import org.springframework.stereotype.Repository;


@Repository
public interface BaseMapping<T> {
    /**
     * 根据ID查询
     * @param id
     * @return
     */
    T selectByPrimaryKey(Integer id);

    /**
     * 插入数据
     * @param entity
     * @return
     */
    Integer insert(T entity);

    /**
     * 插入对象中非空数据
     * @param entity
     * @return
     */
    Integer insertSelective(T entity);

    /**
     * 修改数据
     * @param entity
     * @return
     */
    Integer updateByPrimaryKey(T entity);

    /**
     *
     * @param entity
     * @return
     */
    Integer updateByPrimaryKeySelective(T entity);

    /**
     * 删除数据
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

}
