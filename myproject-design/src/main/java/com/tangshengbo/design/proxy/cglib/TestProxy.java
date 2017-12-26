package com.tangshengbo.design.proxy.cglib;

public class TestProxy {

    public static void main(String[] args) {
        BookFacadeCglibImpl bookFacadeCglib = CGLibProxy.getInstance()
                .getProxy(BookFacadeCglibImpl.class);
        bookFacadeCglib.addBook();
    }
}
