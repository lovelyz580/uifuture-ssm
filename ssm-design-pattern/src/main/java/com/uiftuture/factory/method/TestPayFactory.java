/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uiftuture.factory.method;

import com.uiftuture.factory.method.impl.AliPayImplFactory;
import com.uiftuture.factory.method.impl.WxPayImplFactory;

/**
 * 工厂方法模式测试类
 *
 * @author chenhx
 * @version TestPayFactory.java, v 0.1 2018-07-29 下午 3:43
 */
public class TestPayFactory {
    public static void main(String[] args) {
        //获取ali支付渠道
        PayFactory payFactory = new AliPayImplFactory();
        Pay pay = payFactory.getPay();
        pay.pay();
        //获取wx支付渠道
        PayFactory payFactory2 = new WxPayImplFactory();
        Pay pay2 = payFactory2.getPay();
        pay2.pay();
    }
}