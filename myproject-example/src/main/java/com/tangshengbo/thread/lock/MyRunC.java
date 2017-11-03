package com.tangshengbo.thread.lock;

/**
 * Created by TangShengBo on 2017-11-02.
 */
public class MyRunC implements Runnable {

    private MyService myService;

    public MyRunC(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.methodC();
    }

    public static void main(String[] args) {
        MyRunC myRunC = new MyRunC(new MyService());
        Thread threadA = new Thread(myRunC, "threadA");
        Thread threadB = new Thread(myRunC, "threadB");
        threadA.start();
        threadB.start();
    }
}
