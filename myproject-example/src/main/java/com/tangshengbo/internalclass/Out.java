package com.tangshengbo.internalclass;

/**
 * Created by tangshengbo on 2017/3/14.
 */
public class Out {

    private int age = 12;

    class In {

        private int age = 15;

        public void print() {
            int age = 18;
            System.out.println("局部变量："  + age);
            System.out.println("内部类变量："  + this.age);
            System.out.println("外部类变量："  + Out.this.age);
        }
    }
}
