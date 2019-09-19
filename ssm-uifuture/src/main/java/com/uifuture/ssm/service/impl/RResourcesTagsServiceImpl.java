package com.uifuture.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uifuture.ssm.entity.RResourcesTagsEntity;
import com.uifuture.ssm.mapper.RResourcesTagsMapper;
import com.uifuture.ssm.service.RResourcesTagsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资源标签关系表。 服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@Service
public class RResourcesTagsServiceImpl extends ServiceImpl<RResourcesTagsMapper, RResourcesTagsEntity> implements RResourcesTagsService {

    @Override
    public List<RResourcesTagsEntity> listByResourceId(Integer id) {
        QueryWrapper<RResourcesTagsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(RResourcesTagsEntity.RESOURCE_ID, id);
        return this.list(queryWrapper);
    }
}
