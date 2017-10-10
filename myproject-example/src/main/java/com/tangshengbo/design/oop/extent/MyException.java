package com.tangshengbo.design.oop.extent;

/**
 * Created by Tangshengbo on 2017/10/10.
 */
public class MyException {

    private String message;

    public MyException() {
        System.out.println("MyException construction...............");
    }

    public MyException(String message) {
        this.message = message;
    }
}
