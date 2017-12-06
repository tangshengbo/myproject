package com.tangshengbo.design.template;

/**
 * Created by tangshengbo on 2017/2/19.
 */
public class Tea extends CaffeineBeverage {

    @Override
    void addCondiments() {
        System.out.println("Adding Lemon");
    }

    @Override
    void brew() {
        System.out.println("Steeping the tea");
    }
}
