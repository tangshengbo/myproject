package com.tangshengbo.design.oop.salary;

/**
 * Created by TangShengBo on 2017-09-24.
 */
public class Manager extends AbstractEmployee {

    private double salary;

    public Manager(Payment payment, double salary) {
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
