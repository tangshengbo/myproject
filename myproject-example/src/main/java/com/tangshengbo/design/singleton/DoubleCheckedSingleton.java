package com.tangshengbo.design.singleton;

/**
 * Created by tangshengbo on 2017/2/22.
 * 双检锁
 */
public class DoubleCheckedSingleton {

    private volatile static DoubleCheckedSingleton singleton;

    private DoubleCheckedSingleton() {
    }

    public static DoubleCheckedSingleton getInstance() {
        if (singleton == null) {
            synchronized (DoubleCheckedSingleton.class) {
                if (singleton == null) {
                    singleton = new DoubleCheckedSingleton();
                }
            }
        }
        return singleton;
    }

    public void showMsg() {
        System.out.println("DoubleCheckedSingleton Pattern");
    }
}
