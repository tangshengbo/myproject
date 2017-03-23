package com.tangshengbo.loadclass;

/**
 * Created by tangshengbo on 2017/3/22.
 */
public class Variable {

    private static int staticVar;

    private int instanceVar;

    public Variable() {
        staticVar++;
        instanceVar++;
        System.out.println("staticVar:" + staticVar + "\tinstanceVar:" + instanceVar);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Variable();
        }
    }
}
