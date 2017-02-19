package com.tangshengbo.design.adapter;

/**
 * Created by tangshengbo on 2017/2/19.
 */
public class MallardDuck implements Duck {

    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying");
    }
}
