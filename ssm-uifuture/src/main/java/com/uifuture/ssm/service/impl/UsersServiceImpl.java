package com.uifuture.ssm.service.impl;

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

}
