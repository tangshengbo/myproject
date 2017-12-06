package com.tangshengbo.design.factory;

/**
 * Created by tangshengbo on 2017/2/19.
 */
public class AmericanPizza implements Pizza{

    @Override
    public void taste() {
        System.out.println("American taste");
    }
}
