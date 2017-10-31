package com.tangshengbo.thread.syn;

import java.util.Date;

/**
 * Created by TangShengBo on 2017-10-31.
 */
public class ThreadLocalExt<T> extends ThreadLocal<T> {

    @Override
    protected T initialValue() {
        return (T)new Date();
    }
}
