package com.tangshengbo.design.flyweight;

/**
 * Created by Tangshengbo on 2017/10/12.
 */
public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
