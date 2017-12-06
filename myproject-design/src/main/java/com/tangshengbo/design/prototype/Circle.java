package com.tangshengbo.design.prototype;

/**
 * Created by tangshengbo on 2017/3/5.
 */
public class Circle extends Shape {

    public Circle() {
        type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println(type + ":Draw.................");

    }
}
