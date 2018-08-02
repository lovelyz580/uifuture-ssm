package com.ssm.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 基类
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -1549634521453074321L;
    protected Integer id;
    protected Integer createId;
    protected Integer alterId;
    protected Long createTime;
    protected Long lastAlterTime;
    protected String state;
    protected String description;
}
