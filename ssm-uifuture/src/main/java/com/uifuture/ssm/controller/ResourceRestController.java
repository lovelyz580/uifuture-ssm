package com.uifuture.ssm.controller;

import com.uifuture.ssm.base.BaseController;
import com.uifuture.ssm.convert.ResourceConvert;
import com.uifuture.ssm.entity.ResourceContentEntity;
import com.uifuture.ssm.entity.ResourceEntity;
import com.uifuture.ssm.entity.ResourceSubjectEntity;
import com.uifuture.ssm.entity.ResourceTypeEntity;
import com.uifuture.ssm.entity.UsersEntity;
import com.uifuture.ssm.enums.DeleteEnum;
import com.uifuture.ssm.req.ResourceReq;
import com.uifuture.ssm.result.ResultModel;
import com.uifuture.ssm.service.ResourceService;
import com.uifuture.ssm.service.ResourceSubjectService;
import com.uifuture.ssm.service.ResourceTypeService;
import com.uifuture.ssm.util.ValidateUtils;
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
 * 资源表。 前端控制器
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@RestController
@RequestMapping("/resource")
public class ResourceRestController extends BaseController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ResourceTypeService resourceTypeService;
    @Autowired
    private ResourceSubjectService resourceSubjectService;

    /**
     * 发表资源
     *
     * @return
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public ResultModel submit(ResourceReq resourceReq, HttpServletRequest request, HttpServletResponse response) {
        //校验
        ValidateUtils.validate(resourceReq);

        //获取当前用户
        UsersEntity usersEntity = getLoginInfo(request);
        if (usersEntity == null) {
            return ResultModel.fail();
        }

        //资源信息
        ResourceEntity resourceEntity = ResourceConvert.INSTANCE.toEntity(resourceReq);

        //资源内容
        ResourceContentEntity resourceContentEntity = new ResourceContentEntity();
        resourceContentEntity.setContent(resourceReq.getContent());

        //查询类型
        List<Integer> typeIds = resourceReq.getTypeIds();
        if (!CollectionUtils.isEmpty(typeIds)) {
            Collection<ResourceTypeEntity> resourceTypeEntityList = resourceTypeService.listByIds(typeIds);
            typeIds = new ArrayList<>();
            for (ResourceTypeEntity resourceTypeEntity : resourceTypeEntityList) {
                if (DeleteEnum.NO_DELETE.getValue().equals(resourceTypeEntity.getDeleteTime())) {
                    //未被删除
                    typeIds.add(resourceTypeEntity.getId());
                }
            }
        }
        //查询专题
        List<Integer> subjectIds = resourceReq.getSubjectIds();
        if (!CollectionUtils.isEmpty(subjectIds)) {
            Collection<ResourceSubjectEntity> resourceSubjectEntities = resourceSubjectService.listByIds(subjectIds);
            subjectIds = new ArrayList<>();
            for (ResourceSubjectEntity resourceSubjectEntity : resourceSubjectEntities) {
                if (DeleteEnum.NO_DELETE.getValue().equals(resourceSubjectEntity.getDeleteTime())) {
                    //未被删除
                    subjectIds.add(resourceSubjectEntity.getId());
                }
            }
        }

        resourceService.saveResource(resourceEntity, resourceContentEntity, usersEntity, typeIds, subjectIds, com.uifuture.ssm.util.CollectionUtils.listToSet(resourceReq.getTagsNames()));
        return ResultModel.success("成功");
    }


}
