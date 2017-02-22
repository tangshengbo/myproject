package com.tangshengbo.design.singleton;

/**
 * Created by tangshengbo on 2017/2/22.
 * 枚举方式
 */
public enum  EnumSingleton {

    INSTANCE;

    public void showMsg() {
        System.out.println("EnumSingleton Pattern");
    }
}
