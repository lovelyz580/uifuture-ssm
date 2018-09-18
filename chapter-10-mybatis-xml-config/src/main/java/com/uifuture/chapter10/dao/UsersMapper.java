package com.uifuture.chapter10.dao;


import com.uifuture.chapter10.entity.Users;

public interface UsersMapper {
    /**
     * 根据ID删除数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入所有数据
     *
     * @param record
     * @return
     */
    int insert(Users record);

    /**
     * 插入非空数据
     *
     * @param record
     * @return
     */
    int insertSelective(Users record);

    /**
     * 通过ID查询对象
     *
     * @param id
     * @return
     */
    Users selectByPrimaryKey(Integer id);

    /**
     * 通过id修改非空数据
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Users record);

    /**
     * 通过id修改所有的数据
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Users record);
}