package com.tangshengbo.thread.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Tangshengbo on 2017/11/6.
 */
public class MyLock {

    private AtomicBoolean locked = new AtomicBoolean(false);

    public boolean lock() {
        return locked.compareAndSet(false, true);
    }
}
