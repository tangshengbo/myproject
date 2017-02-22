package com.tangshengbo.design.decorate;

/**
 * Created by tangshengbo on 2017/2/22.
 */
public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "DarkRoast";
    }

    @Override
    public double cost() {
        return 20;
    }
}
