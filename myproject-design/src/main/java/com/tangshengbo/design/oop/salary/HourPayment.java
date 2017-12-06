package com.tangshengbo.design.oop.salary;

/**
 * Created by TangShengBo on 2017-09-24.
 */
public class HourPayment implements Payment {

    @Override
    public double pay(double salary) {
        return 24 * 100;
    }
}
