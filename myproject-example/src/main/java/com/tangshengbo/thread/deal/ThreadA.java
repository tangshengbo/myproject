package com.tangshengbo.thread.deal;

/**
 * Created by TangShengBo on 2017-10-24.
 */
public class ThreadA extends Thread {

    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.methodA();
    }
}
