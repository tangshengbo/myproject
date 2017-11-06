package com.tangshengbo.thread.atomic;

/**
 * Created by Tangshengbo on 2017/11/4.
 */
public class MyRunA implements Runnable {

    private MyService service;

    public MyRunA(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.runMethod();
    }
}
