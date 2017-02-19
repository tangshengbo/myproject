package com.tangshengbo.exception;

import java.math.BigDecimal;

/**
 * Created by tangshengbo on 2017/2/14.
 */
public class ExceptionTest {

    public static void main(String[] args) {
        ExceptionTest test = new ExceptionTest();
        String result = test.testFinally();
        System.out.println(result);
        BigDecimal bigDecimal= BigDecimal.valueOf(0.0);
        if (bigDecimal.doubleValue() <= 0) {
            System.out.println(bigDecimal.intValue());
        }

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
