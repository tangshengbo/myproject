package com.tangshengbo.design.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Tangshengbo on 2017/11/13.
 */
public class CASSingleton implements Serializable {

    private static final AtomicReference<CASSingleton> INSTANCE = new AtomicReference<>();

    private CASSingleton() {
    }

    public static CASSingleton getInstance() {
        while (true) {
            CASSingleton casSingleton = INSTANCE.get();
            if (casSingleton != null) {
                return casSingleton;
            }

            casSingleton = new CASSingleton();
            if (INSTANCE.compareAndSet(null, casSingleton)) {
                return casSingleton;
            }
        }
    }

    /**
     * 严格单例,确保remote instance不会干扰单例模式,避免在发序列化过程中对单例的影响.
     *
     * @return
     * @throws ObjectStreamException
     */
    public Object readResolve() throws ObjectStreamException {
        return INSTANCE.get();
    }
}
