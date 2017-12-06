package com.tangshengbo.design.proxy.example;

/**
 * Created by Tangshengbo on 2017/10/11.
 */
public class Proxy implements Subject {

    private RealSubject realSubject;

    public Proxy() {
        this.realSubject = new RealSubject();
    }

    @Override
    public void request() {
        this.beforeRequest();
        realSubject.request();
        this.afterRequest();
    }

    private void beforeRequest() {
        System.out.println("beforeRequest............");
    }

    private void afterRequest() {
        System.out.println("afterRequest............");
    }
}
