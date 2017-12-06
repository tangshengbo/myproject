package com.tangshengbo.design.oop.extent;

/**
 * Created by Tangshengbo on 2017/10/10.
 */
public class MyRuntimeException extends MyException {

    public MyRuntimeException() {
        System.out.println("MyRuntimeException construction............");
    }

    {
        System.out.println("MyRuntimeException 代码块............");
    }

    public MyRuntimeException(String message) {
        super(message);
    }
}
