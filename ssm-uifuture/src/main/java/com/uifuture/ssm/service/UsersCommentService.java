package com.uifuture.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uifuture.ssm.entity.UsersCommentEntity;

/**
 * <p>
 * 用户评论表 服务类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-21
 */
public interface UsersCommentService extends IService<UsersCommentEntity> {

    /**
     * 软删评论
     *
     * @param commentId
     */
    void updateDeleteTimeById(Integer commentId);
}
