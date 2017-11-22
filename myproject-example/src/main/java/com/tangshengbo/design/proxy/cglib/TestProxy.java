package com.tangshengbo.design.proxy.cglib;

public class TestProxy {

    public static void main(String[] args) {

        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacade bookProxy = (BookFacade) proxy.bind(new BookFacadeImpl());
        bookProxy.addBook();

        BookFacadeCglib bookFacadeCglib = new BookFacadeCglib();
        BookFacadeCglibImpl bookFacadeCglibImpl = (BookFacadeCglibImpl) bookFacadeCglib
                .getInstance(new BookFacadeCglibImpl());
        bookFacadeCglibImpl.addBook();

        StringBuffer sb = new StringBuffer();
        sb.append("tangshengbo");
        sb.deleteCharAt(4);
        System.out.println(sb.toString());
        // System.out.println(bookProxy.addBook());
    }
}
