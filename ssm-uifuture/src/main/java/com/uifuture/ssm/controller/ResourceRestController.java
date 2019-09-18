package com.uifuture.ssm.controller;

import com.uifuture.ssm.base.BaseController;
import com.uifuture.ssm.common.RedisConstants;
import com.uifuture.ssm.convert.ResourceConvert;
import com.uifuture.ssm.dto.FileInfoDTO;
import com.uifuture.ssm.entity.ResourceContentEntity;
import com.uifuture.ssm.entity.ResourceEntity;
import com.uifuture.ssm.entity.ResourceSubjectEntity;
import com.uifuture.ssm.entity.ResourceTypeEntity;
import com.uifuture.ssm.entity.UsersEntity;
import com.uifuture.ssm.enums.DeleteEnum;
import com.uifuture.ssm.enums.ResultCodeEnum;
import com.uifuture.ssm.redis.RedisClient;
import com.uifuture.ssm.req.ResourceReq;
import com.uifuture.ssm.result.ResultModel;
import com.uifuture.ssm.service.ResourceService;
import com.uifuture.ssm.service.ResourceSubjectService;
import com.uifuture.ssm.service.ResourceTypeService;
import com.uifuture.ssm.util.DateUtils;
import com.uifuture.ssm.util.FileUtils;
import com.uifuture.ssm.util.PasswordUtils;
import com.uifuture.ssm.util.ValidateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
     * 用户上传文件的路径
     */
    private static final String FILE_UPLOAD_PATH = "user" + File.separator;
    @Autowired
    private RedisClient redisClient;
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

    /**
     * 上传图片
     *
     * @return
     */
    @RequestMapping(value = "/uploadPictures", method = RequestMethod.POST)
    public ResultModel uploadPictures(HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam("uploadFile") MultipartFile[] uploadFile) throws IOException {
        if (uploadFile.length == 0) {
            return ResultModel.failNoData("请选择文件再上传");
        }
        UsersEntity users = getLoginInfo(request);
        if (users == null) {
            return ResultModel.fail(ResultCodeEnum.USER_NOT_LOGGED);
        }

        //单个用户一天最多上传100张图片
        int times = redisClient.incrInt(RedisConstants.getUploadFileTimesKey(users.getUsername()), RedisConstants.REG_MAX_TIME_1_DAY);
        if (times > 100) {
            return ResultModel.fail(ResultCodeEnum.ALL_TOO_OFTEN);
        }

        List<FileInfoDTO> fileOssUrlDTOList = new ArrayList<>();
        Date date = new Date();
        String dateStr = DateUtils.getDateString(date, "yyyyMM") + "/" + DateUtils.getDateString(date, "dd");
        for (MultipartFile multipartFile : uploadFile) {
            //原文件名称 - 需要带后缀
            String fileName = multipartFile.getOriginalFilename();
            if (StringUtils.isEmpty(fileName)) {
                return ResultModel.fail("文件名为空。原文件名为:" + fileName);
            }
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            if (StringUtils.isEmpty(fileType)) {
                return ResultModel.fail("文件后缀名称错误。原文件名为:" + fileName + "，后缀名为:" + fileType);
            }

            InputStream inputStream = multipartFile.getInputStream();

            //文件上传到 OSS ，oss 路径
            String newFileName = PasswordUtils.getToken() + fileType;
            String path = FILE_UPLOAD_PATH + dateStr + File.separator;
            FileUtils.writeToLocal(path, newFileName, inputStream);

            FileInfoDTO fileInfoDTO = new FileInfoDTO();
            fileInfoDTO.setOldFileName(fileName);
            fileInfoDTO.setNewFileName(newFileName);
            fileInfoDTO.setPath(path);
            fileInfoDTO.setUrl("/");
            fileOssUrlDTOList.add(fileInfoDTO);
            times++;
        }
//        返回文件的存储url，进行上传到阿里云cdn
        return ResultModel.resultModel(200, "上传成功", fileOssUrlDTOList);
    }


}
