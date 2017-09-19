package com.tangshengbo.thread;

/**
 * Created by Tangshengbo on 2017/9/19.
 */
public class ThreadLocalTest {

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return Thread.currentThread().getName();
        }
    };

    public void set() {
        THREAD_LOCAL.set(Thread.currentThread().getName());
    }

    public String get() {
        return THREAD_LOCAL.get();
    }

    public static void main(String[] args) {
        ThreadLocalTest test = new ThreadLocalTest();
//        test.set();
        System.out.println(test.get());
        Thread thread1 = new Thread(() -> {
//            test.set();
            System.out.println(test.get());
        });

        Thread thread2 = new Thread(() -> {
//            test.set();
            System.out.println(test.get());
        });

        try {
            thread1.start();
            thread1.join();
            thread2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
