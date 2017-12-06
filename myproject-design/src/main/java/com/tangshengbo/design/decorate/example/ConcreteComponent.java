package com.tangshengbo.design.decorate.example;

/**
 * Created by TangShengBo on 2017-09-24.
 */
public class ConcreteComponent implements Component {

    @Override
    public void operation() {
        System.out.println("Concrete Component.........");
    }
}
