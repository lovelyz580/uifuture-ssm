/*
 * uifuture.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uifuture.ssm.base.BaseQueryBo;
import com.uifuture.ssm.entity.RResourceSubjectEntity;
import lombok.Data;

/**
 * @author chenhx
 * @version UsersFocusQueryBo.java, v 0.1 2019-09-20 17:31 chenhx
 */
@Data
public class RResourceSubjectQueryBo extends BaseQueryBo {
    /**
     * 资源表id
     */
    private Integer resourceId;
    /**
     * 专题表id
     */
    private Integer subjectId;

    public QueryWrapper<RResourceSubjectEntity> buildQuery() {
        QueryWrapper<RResourceSubjectEntity> queryWrapper = new QueryWrapper<>();
        if (resourceId != null) {
            queryWrapper.eq(RResourceSubjectEntity.RESOURCE_ID, resourceId);

        }
        if (subjectId != null) {
            queryWrapper.eq(RResourceSubjectEntity.SUBJECT_ID, subjectId);

        }
        addSortQuery(queryWrapper);
        addIncludeDeleted(queryWrapper);
        return queryWrapper;
    }
}
