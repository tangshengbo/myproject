package com.tangshengbo.thread.util;

import jodd.util.ThreadUtil;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by TangShengBo on 2017-11-12.
 */
public class SemaphoreTest {

    private static final int THREAD_COUNT = 30;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    private static Semaphore s = new Semaphore(3);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        s.acquire();
                        System.out.println(Thread.currentThread().getName() + "-save data");
                        ThreadUtil.sleep(1000);
                        s.release();
                    } catch (InterruptedException e) {
                        System.out.println(ExceptionUtils.getStackTrace(e));
                    }
                }
            });
        }
        threadPool.shutdown();
    }
}
