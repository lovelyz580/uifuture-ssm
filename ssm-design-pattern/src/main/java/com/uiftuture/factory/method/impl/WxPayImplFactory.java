/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uiftuture.factory.method.impl;

import com.uiftuture.factory.method.Pay;
import com.uiftuture.factory.method.PayFactory;

/**
 * WxPayImpl的具体工厂类
 *
 * @author chenhx
 * @version WxPayImplFactory.java, v 0.1 2018-07-29 下午 3:40
 */
public class WxPayImplFactory implements PayFactory {
    @Override
    public Pay getPay() {
        return new WxPayImpl();
    }
}