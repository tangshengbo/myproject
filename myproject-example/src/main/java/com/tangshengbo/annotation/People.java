package com.tangshengbo.annotation;

/**
 * Created by tangshengbo on 2017/2/21.
 */
@Description(name = {"tang","sheng","bo"}, age = 28, kind = PeopleKind.WHITE)
public class People {

    private String name;

    private int age;

    @Description(name = {"TANG","SHENG","BO"}, age = 18)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
