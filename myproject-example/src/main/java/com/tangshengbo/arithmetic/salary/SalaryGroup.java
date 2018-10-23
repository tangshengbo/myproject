package com.tangshengbo.arithmetic.salary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tangshengbo on 2018/10/18
 */
public class SalaryGroup {

    private String name;
    private Long yearlySalaryTotal = 0L;
    private List<Salary> salarys = new ArrayList<>(60);

    public SalaryGroup(String name) {
        this.name = name;
    }

    public SalaryGroup() {

    }

    public void sum(int yearlySalary) {
        this.yearlySalaryTotal += yearlySalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getYearlySalaryTotal() {
        return yearlySalaryTotal;
    }

    public void setYearlySalaryTotal(Long yearlySalaryTotal) {
        this.yearlySalaryTotal = yearlySalaryTotal;
    }

    public List<Salary> getSalarys() {
        return salarys;
    }

    public void setSalarys(List<Salary> salarys) {
        this.salarys = salarys;
    }
}
