package com.tangshengbo.design.oop.salary;

/**
 * Created by TangShengBo on 2017-09-24.
 */
public class SalaryTest {

    public static void main(String[] args) {
        Employee technical = new Technical(new MonthPayment(), 500);
        technical.showSalary();

        Employee manager = new Manager(new YearPayment(), 1000);
        manager.showSalary();

        Employee parttimer = new Parttimer(new HourPayment(), 100);
        parttimer.showSalary();
    }
}
