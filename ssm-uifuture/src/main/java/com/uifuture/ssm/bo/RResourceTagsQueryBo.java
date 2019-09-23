/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uifuture.ssm.base.BaseQueryBo;
import com.uifuture.ssm.entity.RResourceTagsEntity;
import lombok.Data;

/**
 * @author chenhx
 * @version UsersFocusQueryBo.java, v 0.1 2019-09-20 17:31 chenhx
 */
@Data
public class RResourceTagsQueryBo extends BaseQueryBo {
    /**
     * 资源表id
     */
    private Integer resourceId;
    /**
     * 标签表id
     */
    private Integer tagsId;

    public QueryWrapper<RResourceTagsEntity> buildQuery() {
        QueryWrapper<RResourceTagsEntity> queryWrapper = new QueryWrapper<>();
        if (resourceId != null) {
            queryWrapper.eq(RResourceTagsEntity.RESOURCE_ID, resourceId);

        }
        if (tagsId != null) {
            queryWrapper.eq(RResourceTagsEntity.TAGS_ID, tagsId);

        }
        addSortQuery(queryWrapper);
        addIncludeDeleted(queryWrapper);
        return queryWrapper;
    }
}
