package com.tangshengbo.design.oop.extent;

import java.util.Map;

/**
 * Created by Tangshengbo on 2017/9/29.
 */
public class SubClass extends ParentClass {

    public void init() {
//        this.i = 100;
        this.string = "234234";
        System.out.println(super.string + "\t" + this.string);
    }

    public void doSomething(Map map) {
        System.out.println("子类被执行.........");
    }
}
