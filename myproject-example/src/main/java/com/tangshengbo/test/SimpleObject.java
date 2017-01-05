package com.tangshengbo.test;

public final class SimpleObject {

    private static SimpleObject simpleObject = new SimpleObject();

    private SimpleObject() {
    }

    public static SimpleObject getInstance() {
        return simpleObject;
    }

    public void showMessage() {
        System.out.println("Hello world");
    }

}
