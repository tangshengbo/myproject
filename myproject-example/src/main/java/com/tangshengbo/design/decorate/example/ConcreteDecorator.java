package com.tangshengbo.design.decorate.example;

/**
 * Created by TangShengBo on 2017-09-24.
 */
public class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(Component component) {
        super(component);
    }


    public void operation() {
        System.out.println("穿衣服..............");
        super.operation();
        System.out.println("穿衣服..............");
    }
}
