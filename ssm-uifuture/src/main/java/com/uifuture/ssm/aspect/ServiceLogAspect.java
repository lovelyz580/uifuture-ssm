/*
 * souche.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.uifuture.ssm.aspect;

import com.alibaba.fastjson.JSON;
import com.uifuture.ssm.base.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * service层日志拦截器
 *
 * @author chenhx
 * @version ServiceLogAspect.java, v 0.1 2019-09-18 18:09 chenhx
 */
@Aspect
@Component
@Slf4j
public class ServiceLogAspect {

    /**
     * 通过@Before注解声明一个建言,此建言直接使用拦截规则作为参数
     * 拦截dubbo接口实现
     *
     * @param joinPoint
     */
    @Around("execution(* com.uifuture.ssm.service.impl.*.*(*))")
    public Object around(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        String className = methodSignature.getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();
        Object result = null;
        try {
            log.info("方法名:{}#{}，入参:{}", className, methodName, JSON.toJSONString(args));
            long s = System.currentTimeMillis();
            result = joinPoint.proceed();
            long e = System.currentTimeMillis();
            if (e - s > 1000) {
                log.warn("方法名:{}#{}, 消耗时间:{}ms", className, methodName, (e - s));
            }
        } catch (Throwable throwable) {
            log.error("方法名:" + className + "#" + methodName + "，入参:" + JSON.toJSONString(args) + ",出现异常", throwable);
            if (throwable instanceof BaseException) {
                throw (BaseException) throwable;
            }
            if (throwable instanceof RuntimeException) {
                throw (RuntimeException) throwable;
            }
        }
        return result;
    }

}
