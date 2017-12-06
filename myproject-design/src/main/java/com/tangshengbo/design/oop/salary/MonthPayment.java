package com.tangshengbo.design.oop.salary;

/**
 * Created by TangShengBo on 2017-09-24.
 */
public class MonthPayment implements Payment {

    @Override
    public double pay(double salary) {
        return salary * 160;
    }
}
