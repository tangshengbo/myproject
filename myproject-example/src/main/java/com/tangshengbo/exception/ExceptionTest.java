package com.tangshengbo.exception;

import java.io.IOException;

/**
 * Created by tangshengbo on 2017/2/14.
 */
public class ExceptionTest {

    public static void main(String[] args) {
        ExceptionTest test = new ExceptionTest();
        String result = test.testFinally();
        System.out.println(result);
//        test.calc();
        System.out.println(test.test1());
        System.out.println(test.test2());
        test.test3();


    }

    private String testFinally() {
        try {
            return "try";
        } catch (Exception e) {

        } finally {
            System.out.println("32423423");
//            return "finally";
        }
        return "finally";
    }

    public void calc() {
        try {
            Calc calc = new Calc();
            calc.calc(0);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    private int test1() {
        int ret = 0;
        try {
            return ret;
        } finally {
            ret = 2;
        }
    }

    private int test2() {
        int ret = 0;
        try {
            int a = 5 / 0;
            return ret;
        } finally {
            return 2;
        }
    }

    private void test3() {
        try {
            int a = 5 / 0;
        } finally {
            throw new RuntimeException("除数不能为0");
        }
    }

    public void func() throws IOException {

    }

    public void invokeFunc() {
        try {
            func();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

