package com.tangshengbo.thread.lock;

/**
 * Created by Tangshengbo on 2017/11/15.
 */
public class MyRunD implements Runnable {

    private MyService service;

    public MyRunD(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(service.readByCache(Thread.currentThread().getName() + "-" + i));
        }
    }
}
