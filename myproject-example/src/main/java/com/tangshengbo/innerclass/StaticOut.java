package com.tangshengbo.innerclass;

/**
 * Created by tangshengbo on 2017/3/14.
 */
public class StaticOut {

    private static int age = 12;

    static class In {

        public void print() {
            System.out.println("静态内部类："  + age);
        }
    }
}
