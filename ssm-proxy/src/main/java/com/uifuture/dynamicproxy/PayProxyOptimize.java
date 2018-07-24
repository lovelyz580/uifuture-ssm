/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.uifuture.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理类的优化
 *
 * @author chenhx
 * @version PayProxyOptimize.java, v 0.1 2018-07-24 下午 9:40
 */
public class PayProxyOptimize {
    public <T> T create(Class<T> pay, final T t) {
        return (T) Proxy.newProxyInstance(pay.getClassLoader(),
                //     pay.getInterfaces() //getInterfaces() 确定此对象所表示的类或接口实现的接口,在这里pay是Class类型，所以报异常Exception in thread "main" java.lang.ClassCastException: com.sun.proxy.$Proxy1 cannot be cast to com.uifuture.dynamicproxy.target.Pay
                new Class[]{pay}
                //使用匿名内部类  也可以进行实现InvocationHandler接口
                , new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //在进行执行目标方法前可以进行操作
                        System.out.println("调用之前...");
                        System.out.println("Method:" + method);
                        //调用真实对象的方法，会跳转到代理对象关联的handler对象的invoke方法来进行调用，内部通过类反射实现
                        Object result = method.invoke(t, args);
                        //在进行执行目标方法后可以进行操作
                        System.out.println("调用之后...");
                        return result;
                    }
                });
    }
}