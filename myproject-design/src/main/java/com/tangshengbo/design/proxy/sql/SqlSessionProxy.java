package com.tangshengbo.design.proxy.sql;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Tangshengbo on 2017/10/11.
 */
public class SqlSessionProxy implements InvocationHandler {

    private Object object;

    public SqlSessionProxy(Object object) {
        this.object = object;
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

    @SuppressWarnings("unchecked")
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(), this);
    }
}
