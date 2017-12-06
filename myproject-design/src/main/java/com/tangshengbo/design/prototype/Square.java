package com.tangshengbo.design.prototype;

/**
 * Created by tangshengbo on 2017/3/5.
 */
public class Square extends Shape {

    public Square() {
        type = "Square";
    }

    @Override
    public void draw() {
        System.out.println(type + ":Draw...............");
    }
}
