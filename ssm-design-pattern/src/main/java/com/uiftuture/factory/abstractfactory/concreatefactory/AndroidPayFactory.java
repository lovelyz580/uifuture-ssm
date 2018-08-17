/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uiftuture.factory.abstractfactory.concreatefactory;

import com.uiftuture.factory.abstractfactory.abstractfactory.PayFactory;
import com.uiftuture.factory.abstractfactory.abstractproduct.AliPay;
import com.uiftuture.factory.abstractfactory.abstractproduct.WxPay;
import com.uiftuture.factory.abstractfactory.concreteproduct.AndroidAliPay;
import com.uiftuture.factory.abstractfactory.concreteproduct.AndroidWxPay;

/**
 * 安卓系统下的具体支付工厂类
 *
 * @author chenhx
 * @version AndroidPayFactory.java, v 0.1 2018-07-29 下午 5:23
 */
public class AndroidPayFactory implements PayFactory {
    /**
     * 创建安卓系统下的支付宝渠道对象
     *
     * @return
     */
    @Override
    public AliPay createAliPay() {
        return new AndroidAliPay();
    }

    /**
     * 创建安卓系统下的微信支付渠道对象
     *
     * @return
     */
    @Override
    public WxPay createWxPay() {
        return new AndroidWxPay();
    }
}