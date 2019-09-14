package com.uifuture.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uifuture.ssm.entity.UsersEntity;
import com.uifuture.ssm.mapper.UsersMapper;
import com.uifuture.ssm.service.UsersService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-12
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, UsersEntity> implements UsersService {

    @Override
    public Integer selectCountByUsername(String username) {
        return getCount(username, UsersEntity.USERNAME);
    }

    @Override
    public UsersEntity selectByUsername(String username) {
        return getUsersEntity(username, UsersEntity.USERNAME);
    }


    @Override
    public Integer selectCountByEmail(String email) {
        return getCount(email, UsersEntity.EMAIL);
    }

    @Override
    public UsersEntity selectByEmail(String email) {
        return getUsersEntity(email, UsersEntity.EMAIL);
    }


    /**
     * 通过唯一建获取数据是否存在
     *
     * @param value
     * @param column
     * @return
     */
    private Integer getCount(String value, String column) {
        QueryWrapper<UsersEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(column, value);
        return this.count(queryWrapper);
    }

    /**
     * 通过唯一建查询数据
     *
     * @param value
     * @param column
     * @return
     */
    private UsersEntity getUsersEntity(String value, String column) {
        QueryWrapper<UsersEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(column, value);
        return this.getOne(queryWrapper);
    }
}
