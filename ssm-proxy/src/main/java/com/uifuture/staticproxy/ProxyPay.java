/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.staticproxy;

import com.uifuture.staticproxy.target.AliPay;
import com.uifuture.staticproxy.target.Pay;

/**
 * 代理类
 *
 * @author chenhx
 * @version ProxyPay.java, v 0.1 2018-07-24 下午 8:20
 */
public class ProxyPay implements Pay {
    private Pay pay;

    public ProxyPay() {
        //帮助消费者进行一些操作
        pay = new AliPay("proxyPay");
    }

    public ProxyPay(AliPay aliPay) {
        //当然，可以直接将支付传过来，这样就可以使用消费者自己的
        this.pay = aliPay;
    }

    @Override
    public void pay() {
        System.out.println("代理前...");
        pay.pay();
        System.out.println("代理后...");
    }
}