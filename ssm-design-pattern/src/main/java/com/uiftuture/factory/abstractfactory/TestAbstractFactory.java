/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uiftuture.factory.abstractfactory;

import com.uiftuture.factory.abstractfactory.abstractfactory.PayFactory;
import com.uiftuture.factory.abstractfactory.abstractproduct.AliPay;
import com.uiftuture.factory.abstractfactory.abstractproduct.WxPay;
import com.uiftuture.factory.abstractfactory.concreatefactory.AndroidPayFactory;
import com.uiftuture.factory.abstractfactory.concreatefactory.IOSFactory;

/**
 * 抽象工厂模式测试
 *
 * @author chenhx
 * @version TestAbstractFactory.java, v 0.1 2018-07-29 下午 5:36
 */
public class TestAbstractFactory {
    public static void main(String[] args) {
        PayFactory payFactory;
        AliPay aliPay;
        WxPay wxPay;

        //在Android系统下
        payFactory = new AndroidPayFactory();
        aliPay = payFactory.createAliPay();
        wxPay = payFactory.createWxPay();
        aliPay.appPay();
        wxPay.appPpay();

        //在IOS系统下,仅仅需要改抽象工厂的创建
        payFactory = new IOSFactory();
        aliPay = payFactory.createAliPay();
        wxPay = payFactory.createWxPay();
        aliPay.appPay();
        wxPay.appPpay();
    }
}