/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.staticproxy;

import com.uifuture.staticproxy.target.AliPay;

/**
 * 消费者
 *
 * @author chenhx
 * @version Store.java, v 0.1 2018-07-24 下午 8:24
 */
public class Store {
    public static void main(String[] args) {
        PayProxy payProxy = new PayProxy();
        payProxy.pay();
        System.out.println("-----------------");
        PayProxy payProxy2 = new PayProxy(new AliPay("store"));
        payProxy2.pay();
    }
}