package com.tangshengbo.design.template.check;

/**
 * Created by TangShengBo on 2017-09-14.
 */
public class Person {

    private int id;

    private int age;

    public Person(int id, int age) {
        this.id = id;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", age=" + age +
                '}';
    }
}
