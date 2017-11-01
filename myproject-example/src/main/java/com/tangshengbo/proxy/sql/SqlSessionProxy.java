package com.tangshengbo.proxy.sql;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Tangshengbo on 2017/10/11.
 */
public class SqlSessionProxy implements InvocationHandler {

    private Object object;

    public Object getInstance(Object o) {
        this.object = o;
        Class c = o.getClass();
        return Proxy.newProxyInstance(o.getClass().getClassLoader(), c.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("open session");
        try {
            method.invoke(object, args);
        } finally {
            System.out.println("close session");
        }
        return null;
    }
}
