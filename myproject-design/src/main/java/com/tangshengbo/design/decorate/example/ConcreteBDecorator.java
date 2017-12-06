package com.tangshengbo.design.decorate.example;

/**
 * Created by TangShengBo on 2017-09-24.
 */
public class ConcreteBDecorator extends Decorator {

    public ConcreteBDecorator(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        System.out.println("穿裤子。。。。。。。");
        super.operation();
        System.out.println("穿裤子。。。。。。。");
    }
}
