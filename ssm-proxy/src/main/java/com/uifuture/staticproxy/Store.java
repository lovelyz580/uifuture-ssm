/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.staticproxy;

/**
 * 消费者
 *
 * @author chenhx
 * @version Store.java, v 0.1 2018-07-24 下午 8:24
 */
public class Store {
    public static void main(String[] args) {
        ProxyPay proxyPay = new ProxyPay();
        proxyPay.pay();
        System.out.println("-----------------");
        ProxyPay proxyPay2 = new ProxyPay(new AliPay("store"));
        proxyPay2.pay();
    }
}