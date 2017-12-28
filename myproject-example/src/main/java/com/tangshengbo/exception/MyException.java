package com.tangshengbo.exception;

/**
 * Created by Tangshengbo on 2017/9/27.
 */
public class MyException extends RuntimeException {

    public MyException() {
    }

    public MyException(String msg) {
        super(msg);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        System.out.println("exec fillInStackTrace");
        return this;
    }
}
