/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uiftuture.factory.abstractfactory.concreteproduct;

import com.uiftuture.factory.abstractfactory.abstractproduct.WxPay;

/**
 * 安卓系统下的微信具体支付产品，由工厂负责生产的具体对象
 *
 * @author chenhx
 * @version AndroidWxPay.java, v 0.1 2018-07-29 下午 5:15
 */
public class AndroidWxPay implements WxPay {
    @Override
    public void appPpay() {
        System.out.println("安卓系统下的微信app支付");
    }
}