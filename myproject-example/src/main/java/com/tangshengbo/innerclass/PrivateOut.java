package com.tangshengbo.innerclass;

/**
 * Created by tangshengbo on 2017/3/14.
 */
public class PrivateOut {

    private int age = 12;

    public void print() {
        new In().print();
    }

    private class In {

        public void print() {
            System.out.println("私有内部类:"  + age);
        }
    }
}
