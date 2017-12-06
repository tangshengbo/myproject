package com.tangshengbo.design.singleton;

/**
 * Created by tangshengbo on 2017/2/22.
 */
public class SingletonTest {

    public static void main(String[] args) {
        DoubleCheckedSingleton singleton = DoubleCheckedSingleton.getInstance();
        singleton.showMsg();

        EnumSingleton.INSTANCE.showMsg();
    }
}
