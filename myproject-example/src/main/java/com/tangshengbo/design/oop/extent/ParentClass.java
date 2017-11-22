package com.tangshengbo.design.oop.extent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tangshengbo on 2017/9/29.
 */
public class ParentClass {

    public int i = 100;

    public static final String STRING = "ParentClass";

    public String string = "ParentClass";

    public void doSomething(Map map) {
        System.out.println("父类被执行.........");
    }

    public static void main(String[] args) {
        ParentClass parentClass = new SubClass();
        parentClass.doSomething(new HashMap<>());
    }
}
