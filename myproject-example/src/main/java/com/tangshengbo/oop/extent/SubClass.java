package com.tangshengbo.oop.extent;

/**
 * Created by Tangshengbo on 2017/9/29.
 */
public class SubClass extends ParentClass {

    public void init() {
//        this.i = 100;
        this.string = "234234";
        System.out.println(super.string + "\t" + this.string);
    }

}
