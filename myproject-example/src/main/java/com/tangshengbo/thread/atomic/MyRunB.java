package com.tangshengbo.thread.atomic;

/**
 * Created by Tangshengbo on 2017/11/4.
 */
public class MyRunB implements Runnable {

    private MyService service;

    public MyRunB(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
         service.stopMethod();
    }
}
