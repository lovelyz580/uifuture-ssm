package com.uifuture.ssm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uifuture.ssm.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author chenhx
 * @since 2019-09-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("users")
public class UsersEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * QQ互联中，获取的Access Token，得到对应用户身份的OpenID,OpenID是此网站上或应用中唯一对应用户身份的标识，网站或应用可将此ID进行存储，便于用户下次登录时辨识其身份，或将其与用户在网站上或应用中的原有账号进行绑定。  用户的ID，与QQ号码一一对应。
     */
    private String qqOpenid;

    /**
     * 用户主页图片地址-默认值为默认图片的地址
     */
    private String headImage;

    /**
     * 用户名(唯一而且不能有中文)-用户可以在注册后修改，修改一次90U币
     */
    private String username;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 密码(使用MD5+盐加密)
     */
    private String password;

    /**
     * 盐(密码加密时使用)
     */
    private String salt;

    /**
     * 手机号(注册后必须绑定手机号)
     */
    private String mobilePhone;

    /**
     * 性别(默认为0)0-保密 1-男 2-女
     */
    private Integer sex;

    /**
     * 出生日期(默认为NULL)
     */
    private LocalDate birthday;

    /**
     * 邮箱(默认为NULL)1-激活0-未激活 2-未填写邮箱
     */
    private String email;

    /**
     * 微信号
     */
    private String weixin;

    /**
     * qq号
     */
    private String qq;

    /**
     * 邮箱激活状态(默认为NULL)1-激活0-未激活 2-未绑定邮箱（多线程操作时，一定要先判断再修改值，否则容易出错）
     */
    private String mailboxState;

    /**
     * 当前账户可用U币(默认为0)
     */
    private Integer ub;

    /**
     * 支付宝账号(默认为NULL)
     */
    private String alipayAccountNumber;

    /**
     * 支付宝真实姓名(默认为NULL)
     */
    private String alipayRealName;

    /**
     * 实名认证状态 0-未实名认证 1-审核中 2-实名认证通过 3-实名认证失败
     */
    private Boolean realNameState;

    /**
     * 描述
     */
    private String description;

    /**
     * 修改人的id
     */
    private Integer updateId;

    /**
     * 用户类型 0-用户 1-管理员 - 默认为0-也就是用户
     */
    private Boolean type;

    /**
     * 用户状态(默认为1)1-正常 0-被封号
     */
    private Boolean state;

    /**
     * 创建者id
     */
    private Integer createId;


}
