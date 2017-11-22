package com.tangshengbo.design.proxy.cglib;

public class BookFacadeImpl implements BookFacade {

    public void addBook() {
        System.out.println("增加图书方法。。。");

    }

    @Override
    public void same() {
        String str = "kksksk";

        System.out.println("老梁看大开的");
    }


}
