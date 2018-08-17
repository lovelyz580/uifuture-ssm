/**
 * uifuture.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uiftuture.factory.method.impl;

import com.uiftuture.factory.method.Pay;
import com.uiftuture.factory.method.PayFactory;

/**
 * AliPayImpl的具体工厂方法
 *
 * @author chenhx
 * @version AliPayFactory.java, v 0.1 2018-07-29 下午 3:37
 */
public class AliPayImplFactory implements PayFactory {
    /**
     * 返回具体的实例对象
     *
     * @return
     */
    @Override
    public Pay getPay() {
        return new AliPayImpl();
    }
}