package com.uifuture.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uifuture.ssm.entity.UsersCommentEntity;
import com.uifuture.ssm.mapper.UsersCommentMapper;
import com.uifuture.ssm.service.UsersCommentService;
import com.uifuture.ssm.util.DateUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户评论表 服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-21
 */
@Service
public class UsersCommentServiceImpl extends ServiceImpl<UsersCommentMapper, UsersCommentEntity> implements UsersCommentService {

    @Override
    public void updateDeleteTimeById(Integer commentId) {
        QueryWrapper<UsersCommentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(UsersCommentEntity.ID, commentId);
        UsersCommentEntity usersCommentEntity = new UsersCommentEntity();
        usersCommentEntity.setDeleteTime(DateUtils.getIntDateTimeS());
        this.update(usersCommentEntity, queryWrapper);
    }
}
