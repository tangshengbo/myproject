package com.tangshengbo.design.template;

/**
 * Created by tangshengbo on 2017/2/19.
 */
public class Coffee extends CaffeineBeverage {

    @Override
    void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }

    @Override
    void brew() {
        System.out.println("Dripping coffee through filter");
    }
}
