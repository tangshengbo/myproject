package com.tangshengbo.design.visitor.example;

/**
 * Created by Tangshengbo on 2017/10/18.
 */
public abstract class Person {

    private String name;

    public Person(String name) {
        this.name = name;
    }

    public abstract String accept(Action visitor);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
