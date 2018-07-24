/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.dynamicproxy;

import com.uifuture.dynamicproxy.target.AliPay;
import com.uifuture.dynamicproxy.target.Pay;

/**
 * 调用优化后的代理类
 *
 * @author chenhx
 * @version StoreOptimize.java, v 0.1 2018-07-24 下午 9:53
 */
public class StoreOptimize {
    public static void main(String[] args) {
        Pay realPay = new AliPay();
        Pay aliPay = new PayProxyOptimize().create(Pay.class, realPay);
        aliPay.pay("测试");
    }
}