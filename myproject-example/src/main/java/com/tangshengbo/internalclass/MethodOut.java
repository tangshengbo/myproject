package com.tangshengbo.internalclass;

/**
 * Created by tangshengbo on 2017/3/14.
 */
public class MethodOut {

    private int age = 12;

    public void print(final int x) {
        class In {

            public void inPrint() {
                System.out.println(x);
                System.out.println(age);
            }
        }
        new In().inPrint();
    }
}
