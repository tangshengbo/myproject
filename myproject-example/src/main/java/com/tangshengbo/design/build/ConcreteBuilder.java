package com.tangshengbo.design.build;

/**
 * Created by Tangshengbo on 2017/10/12.
 */
public class ConcreteBuilder implements Builder {

    @Override
    public void buildPartA() {
        System.out.println("buildPartA.............");
    }

    @Override
    public void buildPartB() {
        System.out.println("buildPartB.............");
    }

    @Override
    public void buildPartC() {
        System.out.println("buildPartC.............");
    }

    @Override
    public Product getResult() {
        return new ConcreteProduct();
    }
}
