package com.tangshengbo.refactor.skill.employee;

/**
 * Created by TangShengBo on 2017-03-28.
 */
public class Employee {

    private int type;

    public static final int ENGINEER = 0;

    public static final int SALESMAN = 1;

    public static final int MANAGER = 2;

    /**
     * Replace Constructor with Factory Method（以工厂函数取代构造函数）
     * @param name
     * @return
     */
    public static Employee create(String name) {
        try {
            return (Employee) Class.forName(name).newInstance();
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("不支持类型");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 以明确函数创建子类 （适用于子类比较少且不再变化）
     * @return
     */
    public static Employee createManager() {
        return new Manager();
    }

    public static Employee createEngineer() {
        return new Engineer();
    }

    public static Employee createSalesman() {
        return new Salesman();
    }

}
