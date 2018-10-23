package com.tangshengbo.arithmetic.salary;

/**
 * Created by Tangshengbo on 2018/10/18
 */
public class Salary implements Cloneable {

    // 用于克隆降低new构造
    private static Salary cloneSalary = new Salary();
    private String name;
    private int baseSalary;
    private int bonus;

    /** 获取年薪 */
    public int yearlySalary() {
        return baseSalary * 13 + bonus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    /**
     * 解析一行数据,并返回对象
     * @param line
     * @return
     */
    public static Salary parse(String line) {
        try {
            String[] salaryPart = line.split("\t");
            String name = salaryPart[0];
            int baseSalary = Integer.valueOf(salaryPart[1]);
            int bonus = Integer.valueOf(salaryPart[2]);

            Salary newSalary = (Salary) cloneSalary.clone();
//            Salary newSalary = new Salary();
            newSalary.setName(name);
            newSalary.setBaseSalary(baseSalary);
            newSalary.setBonus(bonus);
            return newSalary;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Salary newSalary(String name, int baseSalary, int bonus) {
        try {
            Salary newSalary = (Salary) cloneSalary.clone();
            newSalary.setName(name);
            newSalary.setBaseSalary(baseSalary);
            newSalary.setBonus(bonus);
            return newSalary;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
