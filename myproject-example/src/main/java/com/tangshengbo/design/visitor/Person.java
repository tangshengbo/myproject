package com.tangshengbo.design.visitor;

/**
 * Created by Tangshengbo on 2017/10/18.
 */
public abstract class Person {

    protected String name;

    protected String action;

    public Person(String name, String action) {
        this.name = name;
        this.action = action;
    }

    public abstract String getConclusion();
}
