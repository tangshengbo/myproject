package com.tangshengbo.thread.lock;

import jodd.util.ThreadUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Tangshengbo on 2017/11/15.
 */
public class MyCache {

    private static final Map<String, Object> map = new HashMap<>();
    private static final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private static final Lock read = rwl.readLock();
    private static final Lock write = rwl.writeLock();

    /**
     * 获取一个key对应的value
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        read.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在读取...");
            ThreadUtil.sleep(500);
            return map.get(key);
        } finally {
            read.unlock();
            System.out.println(Thread.currentThread().getName() + " 释放了读锁");
        }
    }

    /**
     * 设置key对应的value，并返回旧的value
     *
     * @param key
     * @param value
     * @return
     */
    public static Object put(String key, Object value) {
        write.lock();
        try {
            System.err.println(Thread.currentThread().getName() + " 正在写入...");
            ThreadUtil.sleep(500);
            return map.put(key, value);
        } finally {
            write.unlock();
            System.err.println(Thread.currentThread().getName() + " 释放了写锁");
        }
    }

    /**
     * 清空所有的内容
     */
    public static void clear() {
        write.lock();
        try {
            map.clear();
        } finally {
            write.unlock();
        }
    }
}
