package com.tangshengbo.design.adapter;

/**
 * Created by tangshengbo on 2017/2/19.
 */
public class WildTurkey implements Turkey {

    @Override
    public void gobble() {
        System.out.println("Gobble gobble");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying a short distance");
    }
}
