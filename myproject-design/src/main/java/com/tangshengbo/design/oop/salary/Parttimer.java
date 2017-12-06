package com.tangshengbo.design.oop.salary;

/**
 * Created by TangShengBo on 2017-09-24.
 */
public class Parttimer extends AbstractEmployee {

    private double salary;

    public Parttimer(Payment payment, double salary) {
        super(payment);
        this.salary = salary;
    }

    @Override
    public void showSalary() {
        super.showSalary();
    }

    @Override
    protected double getCurrentSalary() {
        return salary;
    }
}
