package com.uifuture.ssm.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有实体类的超类
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -1549634521453074321L;
    /**
     * 主键，唯一标识符
     */
    protected Integer id;
    /**
     * 创建时间
     */
    protected Date createTime;
    /**
     * 更新时间
     */
    protected Date updateTime;
    /**
     * 删除时间，0-未删除，单位秒
     */
    protected Integer deleteTime;
}
