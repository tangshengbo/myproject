package com.tangshengbo.design.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Created by TangShengBo on 2017-11-09.
 */
public class HolderSingleton implements Serializable {

    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class SingletonHolder {
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static final HolderSingleton SINGLETON = new HolderSingleton();
    }

    private HolderSingleton() {}

    public static HolderSingleton getInstance() {
        return SingletonHolder.SINGLETON;
    }

    /**
     * 严格单例,确保remote instance不会干扰单例模式,避免在发序列化过程中对单例的影响.
     * @return
     * @throws ObjectStreamException
     */
    public Object readResolve() throws ObjectStreamException {
        return SingletonHolder.SINGLETON;
    }
}
