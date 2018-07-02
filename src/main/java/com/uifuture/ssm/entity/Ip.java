package com.uifuture.ssm.entity;

import lombok.Data;

/**
 * ip
 */
@Data
public class Ip extends BaseEntity {
    //�û�id
    private Integer userId;//���id
    //ip��ַ
    private String ip; //��¼ip
    //��¼ʱ��
    private long loginTime; //��¼ʱ��

    public Ip() {
    }

    public Ip(Integer userId, String ip, long loginTime) {
        this.userId = userId;
        this.ip = ip;
        this.loginTime = loginTime;
    }
}