package com.tangshengbo.internalclass;

/**
 * Created by tangshengbo on 2017/3/14.
 */
public class PrivateOut {

    private int age = 12;

    private class In {

        public void print() {
            System.out.println("私有内部类:"  + age);
        }
    }
}
