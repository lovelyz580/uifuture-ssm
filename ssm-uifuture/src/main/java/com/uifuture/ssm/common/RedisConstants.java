/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.common;

/**
 * Redis key键
 *
 * @author chenhx
 * @version RedisConstants.java, v 0.1 2019-09-14 22:10 chenhx
 */
public class RedisConstants {

    /**
     * 一定时间内请求注册的最大的次数
     */
    public static final Integer REG_MAX_TIMES = 20;
    /**
     * 一定时间内发送邮件的最大次数
     */
    public static final Integer SEND_CODE_MAX_TIMES = 10;
    /**
     * 时间，10分钟
     */
    public static final Long REG_MAX_TIME = 10 * 60L;
    private static final String REDIS_PREFIX = "ssm.";

    /**
     * 注册，获取当前ip请求的注册次数
     *
     * @param ip
     * @return
     */
    public static String getRegTimesKey(String ip) {
        return REDIS_PREFIX + "getRegTimesKey." + ip;
    }

    /**
     * 注册，获取验证码
     *
     * @param email
     * @return
     */
    public static String getRegEmailKey(String email) {
        return REDIS_PREFIX + "getRegEmailKey." + email;
    }

    /**
     * 获取发送的次数
     *
     * @param ip
     * @return
     */
    public static String getSendEmailCodeTimesKey(String ip) {
        return REDIS_PREFIX + "getSendEmailCodeTimesKey." + ip;
    }


}
