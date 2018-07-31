/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uiftuture.strategy.impl;

import com.uiftuture.strategy.Strategy;

import java.math.BigDecimal;

/**
 * 中午时间段具体的打折策略角色
 *
 * @author chenhx
 * @version NooningStrategyImpl.java, v 0.1 2018-07-31 下午 6:18
 */
public class NooningStrategyImpl extends Strategy {
    /**
     * 进行促销打折活动
     *
     * @param price 实际价格
     * @return
     */
    @Override
    public BigDecimal discount(BigDecimal price) {
        System.out.println("实际价格:" + price.doubleValue());
        //直接返回折扣的金额
        return price.multiply(new BigDecimal(0.6));
    }
}