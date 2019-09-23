package com.uifuture.ssm.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.uifuture.ssm.base.BaseController;
import com.uifuture.ssm.base.page.Page;
import com.uifuture.ssm.bo.RResourceTypeQueryBo;
import com.uifuture.ssm.convert.ResourceConvert;
import com.uifuture.ssm.dto.ResourcePageDTO;
import com.uifuture.ssm.entity.RResourceTypeEntity;
import com.uifuture.ssm.entity.ResourceEntity;
import com.uifuture.ssm.entity.ResourceTypeEntity;
import com.uifuture.ssm.enums.DeleteEnum;
import com.uifuture.ssm.enums.ResultCodeEnum;
import com.uifuture.ssm.exception.CheckoutException;
import com.uifuture.ssm.result.ResultModel;
import com.uifuture.ssm.service.RResourceSubjectService;
import com.uifuture.ssm.service.RResourceTypeService;
import com.uifuture.ssm.service.ResourceService;
import com.uifuture.ssm.service.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 资源分类表。 前端控制器
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@RestController
@RequestMapping("/resourceType")
public class ResourceTypeRestController extends BaseController {
    @Autowired
    private ResourceTypeService resourceTypeService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RResourceSubjectService rResourceSubjectService;

    @Autowired
    private RResourceTypeService rResourceTypeService;

    /**
     * 获取分类的资源 列表，分页
     *
     * @return
     */
    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    public ResultModel pageList(Integer typetId, Integer pageNum, Integer pageSize, HttpServletRequest
            request, HttpServletResponse response) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 20;
        }
        if (typetId == null || typetId < 0) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }
        //获取分类
        ResourceTypeEntity resourceTypeEntity = resourceTypeService.getById(typetId);
        if (resourceTypeEntity == null) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }
        if (!DeleteEnum.NO_DELETE.getValue().equals(resourceTypeEntity.getDeleteTime())) {
            throw new CheckoutException(ResultCodeEnum.DATA_DOES_NOT_EXIST);
        }

        RResourceTypeQueryBo rResourceTypeQueryBo = new RResourceTypeQueryBo();
        rResourceTypeQueryBo.setResourceTypeId(typetId);
        rResourceTypeQueryBo.buildQuery();
        IPage<RResourceTypeEntity> entityIPage = rResourceTypeService.getPage(pageNum, pageSize, rResourceTypeQueryBo);

        List<RResourceTypeEntity> rResourceSubjectEntities = entityIPage.getRecords();
        List<Integer> resourceIds = new ArrayList<>();
        for (RResourceTypeEntity rResourceSubjectEntity : rResourceSubjectEntities) {
            //资源id
            resourceIds.add(rResourceSubjectEntity.getResourceId());
        }
        Collection<ResourceEntity> resourceEntities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(resourceIds)) {
            resourceEntities = resourceService.listByIds(resourceIds);
        }

        Page<ResourcePageDTO> resourcePageDTOPage = new Page<>();
        resourcePageDTOPage.setPageSize((int) entityIPage.getSize());
        resourcePageDTOPage.setCurrentIndex((int) entityIPage.getCurrent());
        resourcePageDTOPage.setTotalNumber((int) entityIPage.getTotal());

        List<ResourcePageDTO> resourcePageDTOS = ResourceConvert.INSTANCE.entityToPageList(resourceEntities);
        resourcePageDTOPage.setItems(resourcePageDTOS);
        return ResultModel.success(resourcePageDTOPage);
    }


}
