package com.uifuture.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uifuture.ssm.entity.RResourcesTagsEntity;

import java.util.List;

/**
 * <p>
 * 资源标签关系表。 服务类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
public interface RResourcesTagsService extends IService<RResourcesTagsEntity> {

    /**
     * 通过资源id查询标签 数据
     *
     * @param id
     * @return
     */
    List<RResourcesTagsEntity> listByResourceId(Integer id);
}
