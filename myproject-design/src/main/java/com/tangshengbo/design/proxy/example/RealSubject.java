package com.tangshengbo.design.proxy.example;

/**
 * Created by Tangshengbo on 2017/10/11.
 */
public class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("RealSubject request.........");
    }
}
