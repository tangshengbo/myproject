package com.tangshengbo.thread.exception;

/**
 * Created by Tangshengbo on 2017/11/10.
 */
public class MyService implements Runnable {

    @Override
    public void run() {
        String username = null;
        System.out.println(username.hashCode());
    }

    public static void main(String[] args) {
        MyService service = new MyService();
        Thread threadA = new Thread(service, "threadA");
        Thread threadB = new Thread(service, "threadB");
        MyUncaughtExceptionHandler handler = new MyUncaughtExceptionHandler();
        threadA.setUncaughtExceptionHandler(handler);
        threadB.setUncaughtExceptionHandler(handler);
        threadA.start();
        threadB.start();
    }
}
