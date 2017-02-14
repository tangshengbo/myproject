package com.tangshengbo.exception;

/**
 * Created by tangshengbo on 2017/2/14.
 */
public class ExceptionTest {

    public static void main(String[] args) {
        ExceptionTest test = new ExceptionTest();
        String result = test.testFinally();
        System.out.println(result);

    }

    public String testFinally() {
        try {
            return "try";
        }catch (Exception e) {

        }finally {
           // return "finally";
        }
        return "finally";
    }

}
