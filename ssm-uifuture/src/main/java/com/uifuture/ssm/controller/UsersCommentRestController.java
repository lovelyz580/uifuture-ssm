package com.uifuture.ssm.controller;

import com.uifuture.ssm.base.BaseController;
import com.uifuture.ssm.convert.UsersCommentConvert;
import com.uifuture.ssm.entity.ResourceEntity;
import com.uifuture.ssm.entity.UsersCommentEntity;
import com.uifuture.ssm.entity.UsersEntity;
import com.uifuture.ssm.enums.ResultCodeEnum;
import com.uifuture.ssm.enums.UsersCommentEnum;
import com.uifuture.ssm.exception.CheckoutException;
import com.uifuture.ssm.req.UsersCommentReq;
import com.uifuture.ssm.result.ResultModel;
import com.uifuture.ssm.service.ResourceService;
import com.uifuture.ssm.service.UsersCommentService;
import com.uifuture.ssm.util.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户评论表 前端控制器
 * </p>
 *
 * @author chenhx
 * @since 2019-09-21
 */
@RestController
@RequestMapping("/usersComment")
public class UsersCommentRestController extends BaseController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UsersCommentService usersCommentService;

    /**
     * 用户进行评论
     *
     * @return
     */
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public ResultModel comment(UsersCommentReq usersCommentReq, HttpServletRequest request, HttpServletResponse response) {
        //参数校验
        ValidateUtils.validate(usersCommentReq);
        //判断资源是否存在
        ResourceEntity resourceEntity = resourceService.getById(usersCommentReq.getResourceId());
        if (resourceEntity == null) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }
        UsersEntity usersEntity = getLoginInfo(request, true);

        UsersCommentEntity usersCommentEntity = UsersCommentConvert.INSTANCE.reqToEntity(usersCommentReq);
        usersCommentEntity.setUserId(usersEntity.getId());
        //TODO 敏感词需要过滤
        usersCommentEntity.setDetails(usersCommentReq.getRealDetails());
        usersCommentEntity.setState(UsersCommentEnum.NORMAL.getValue());
        usersCommentService.save(usersCommentEntity);
        return ResultModel.success();
    }


    /**
     * 用户删除评论
     *
     * @return
     */
    @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
    public ResultModel deleteComment(Integer commentId, HttpServletRequest request, HttpServletResponse response) {
        if (commentId == null || commentId < 1) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }
        //获取登录用户
        UsersEntity usersEntity = getLoginInfo(request, true);
        //获取评论
        UsersCommentEntity usersCommentEntity = usersCommentService.getById(commentId);
        if (usersCommentEntity == null) {
            throw new CheckoutException(ResultCodeEnum.PARAMETER_ERROR);
        }
        if (!usersCommentEntity.getUserId().equals(usersEntity.getId())) {
            //无权限删除
            throw new CheckoutException(ResultCodeEnum.NO_PRIVILEGE);
        }
        //软删评论
        usersCommentService.updateDeleteTimeById(commentId);
        return ResultModel.success();
    }


    /**
     * TODO 用户编辑评论
     *
     * @return
     */
    @RequestMapping(value = "/updateComment", method = RequestMethod.POST)
    public ResultModel updateComment(HttpServletRequest request, HttpServletResponse response) {
        return ResultModel.success();
    }

    /**
     * TODO 获取资源的评论 列表，分页
     *
     * @return
     */
    @RequestMapping(value = "/pageResourceList", method = RequestMethod.POST)
    public ResultModel pageResourceList(HttpServletRequest request, HttpServletResponse response) {
        return ResultModel.success();
    }

    /**
     * TODO 获取用户的评论 列表，分页
     *
     * @return
     */
    @RequestMapping(value = "/pageUsersList", method = RequestMethod.POST)
    public ResultModel pageUsersList(HttpServletRequest request, HttpServletResponse response) {
        return ResultModel.success();
    }


}
