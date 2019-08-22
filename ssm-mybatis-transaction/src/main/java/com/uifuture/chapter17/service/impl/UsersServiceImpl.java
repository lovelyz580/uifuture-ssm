package com.uifuture.chapter17.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uifuture.chapter17.domain.entity.UsersEntity;
import com.uifuture.chapter17.mapper.UsersMapper;
import com.uifuture.chapter17.service.IUsersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2019-08-23
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, UsersEntity> implements IUsersService {
    @Override
    public Boolean updateByUsername(UsersEntity usersEntity) {
        if (usersEntity == null) {
            return false;
        }
        if (StringUtils.isEmpty(usersEntity.getUsername())) {
            return false;
        }
        QueryWrapper<UsersEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(UsersEntity.USERNAME, usersEntity.getUsername());
        return this.update(usersEntity, queryWrapper);
    }

    @Override
    public Boolean updateMoneyByUsername(Integer money, String username) {
        if (money == null) {
            return false;
        }
        if (StringUtils.isEmpty(username)) {
            return false;
        }
        UpdateWrapper<UsersEntity> updateWrapper = new UpdateWrapper<>();
        //拼接sql
        updateWrapper.setSql(UsersEntity.MONEY + "=" + UsersEntity.MONEY + "+" + money);
        updateWrapper.eq(UsersEntity.USERNAME, username);
        return this.update(updateWrapper);
    }
}
