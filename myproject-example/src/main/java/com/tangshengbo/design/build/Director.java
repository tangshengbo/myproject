package com.tangshengbo.design.build;

/**
 * Created by Tangshengbo on 2017/10/12.
 */
public class Director {

    private Builder builder;

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public Product construct() {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getResult();
    }
}
