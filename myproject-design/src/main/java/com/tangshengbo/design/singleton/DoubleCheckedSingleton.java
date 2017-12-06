package com.tangshengbo.design.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Created by tangshengbo on 2017/2/22.
 * 双检锁
 */
public class DoubleCheckedSingleton implements Serializable {

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

    /**
     * 严格单例,确保remote instance不会干扰单例模式,避免在发序列化过程中对单例的影响.
     * @return
     * @throws ObjectStreamException
     */
    public Object readResolve() throws ObjectStreamException {
        return singleton;
    }
}
