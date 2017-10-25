package com.tangshengbo.thread.lock;

/**
 * Created by TangShengBo on 2017-10-25.
 */
public class MyRunA implements Runnable {

    private MyService myService;

    public MyRunA(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
       myService.methodA();
    }
}

