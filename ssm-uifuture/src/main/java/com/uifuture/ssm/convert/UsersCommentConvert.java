/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.convert;

import com.uifuture.ssm.entity.UsersCommentEntity;
import com.uifuture.ssm.req.UsersCommentReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author chenhx
 * @version UsersCommentConvert.java, v 0.1 2019-09-21 11:17 chenhx
 */
@Mapper
public interface UsersCommentConvert {

    UsersCommentConvert INSTANCE = Mappers.getMapper(UsersCommentConvert.class);

    /**
     * @param entity
     * @return
     */
    UsersCommentReq entityToReq(UsersCommentEntity entity);

    /**
     * @param entity
     * @return
     */
    UsersCommentEntity reqToEntity(UsersCommentReq req);

}
