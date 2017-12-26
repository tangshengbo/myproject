package com.tangshengbo.design.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by TangShengBo on 2017/12/26.
 */
public class CGLibProxy implements MethodInterceptor {

    private static final CGLibProxy proxy = new CGLibProxy();

    private CGLibProxy() {

    }

    public static CGLibProxy getInstance() {
        return proxy;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(obj, args);
        after();
        return result;
    }

    private void before() {
        System.out.println("before...................");
    }
    private void after() {
        System.out.println("after...................");
    }
}
