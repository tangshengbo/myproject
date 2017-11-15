package com.tangshengbo.thread.lock;

/**
 * Created by Tangshengbo on 2017/11/15.
 */
public class MyRunE implements Runnable {

    private MyService service;

    public MyRunE(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(service.writeByCache(Thread.currentThread().getName() + "-" + i,
                    Thread.currentThread().getName() + "-" + i));
        }
    }
}
