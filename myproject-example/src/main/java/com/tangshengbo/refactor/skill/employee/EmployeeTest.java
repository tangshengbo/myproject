package com.tangshengbo.refactor.skill.employee;

/**
 * Created by TangShengBo on 2017-03-28.
 */
public class EmployeeTest {

    public static void main(String[] args) {
        Employee employee = Employee.create("com.tangshengbo.refactor.skill.employee.Manager");
        System.out.println(employee);

        Employee manager = Employee.createManager();
        System.out.println(manager);

        Employee engineer = Employee.createEngineer();
        System.out.println(engineer);

        Employee salesman = Employee.createSalesman();
        System.out.println(salesman);
    }
}
