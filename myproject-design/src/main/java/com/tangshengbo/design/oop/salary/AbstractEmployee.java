package com.tangshengbo.design.oop.salary;

/**
 * Created by TangShengBo on 2017-09-24.
 */
public abstract class AbstractEmployee implements Employee {

    protected Payment payment;


    public AbstractEmployee(Payment payment) {
        this.payment = payment;
    }

    @Override
    public void showSalary() {
        System.out.println(payment.pay(getCurrentSalary()));
    }

    protected abstract double getCurrentSalary();
}
