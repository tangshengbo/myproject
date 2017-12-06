package com.tangshengbo.design.visitor.example;

/**
 * Created by Tangshengbo on 2017/10/18.
 */
public class Man extends Person {

    public Man(String name) {
        super(name);
    }

    @Override
    public String accept(Action visitor) {
        return visitor.getManConclusion(this);
    }
}
