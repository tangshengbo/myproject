package com.tangshengbo.thread.syn;

import java.util.concurrent.TimeUnit;

/**
 * Created by Tangshengbo on 2017/10/24.
 */
public class ObjectService {

    public void serviceMethod() {
        try {
            synchronized (this) {
                System.out.println("当前线程：" + Thread.currentThread().getName());
                System.out.println("begin time：" + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(2);
                System.out.println("end time:" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
