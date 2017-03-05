package com.tangshengbo.design.prototype;

/**
 * Created by tangshengbo on 2017/3/5.
 */
public class Rectangle extends Shape {

    public Rectangle() {
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println(type + ":Draw.............");
    }
}
