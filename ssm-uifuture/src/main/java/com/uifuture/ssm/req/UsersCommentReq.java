package com.uifuture.ssm.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <p>
 * 用户评论
 * </p>
 *
 * @author chenhx
 * @since 2019-09-21
 */
@Data
public class UsersCommentReq implements Serializable {
    private static final long serialVersionUID = 4854935929191869821L;
    /**
     * 资源id
     */
    @NotEmpty(message = "评论的资源不能为空")
    private Integer resourceId;

    /**
     * 父类的评论id
     */
    private Integer pid = 0;
    /**
     * 评论id
     */
    private Integer commentId = 0;
    /**
     * 实际的评论内容
     */
    @NotEmpty(message = "评论内容不能为空")
    private String realDetails;
}
