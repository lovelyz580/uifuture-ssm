package com.ssm.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: �º���.
 * Date: 2017/3/18.
 * Time: ���� 9:55.
 * Explain:����ʵ����ĳ���
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -1549634521453074321L;
    // �൱��java������֤����Ҫ���ڰ汾����
    protected Integer id;  //������Ψһ��ʶ��
    protected Integer createId;  //������ID
    protected Integer alterId;  //����޸���ID
    protected Long createTime; //����ʱ��
    protected Long lastAlterTime; //������ʱ��
    protected String state; //ɾ����־ ��1 ������ 0 ɾ����
    protected String description; //����

    public BaseEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
