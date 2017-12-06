package com.tangshengbo.design.proxy.log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Created by TangShengBo on 2017-09-12.
 */
public class CheckLogProxy implements InvocationHandler {

    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        Class targetClass = this.target.getClass();
        return Proxy.newProxyInstance(targetClass.getClassLoader(), targetClass.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass().getName());
        long starTime = System.currentTimeMillis();
        System.out.println("当前方法名:" + method.getName() + "参数:" + Arrays.toString(args));
        Object result = method.invoke(target, args);
        long endTime = System.currentTimeMillis();
        System.out.println("方法返回值:" + result + "耗时:" + (endTime - starTime) / 1000.0 + "s");
        return result;
    }
}
