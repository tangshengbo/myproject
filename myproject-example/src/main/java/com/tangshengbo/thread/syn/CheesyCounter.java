package com.tangshengbo.thread.syn;

import jodd.util.ThreadUtil;

/**
 * Created by Tangshengbo on 2017/12/19.
 */
public class CheesyCounter {
    // Employs the cheap read-write lock trick
    // All mutative operations MUST be done with the 'this' lock held
    private volatile int value;

    //读操作，没有synchronized，提高性能
    public int getValue() {
        return value;
    }

    //写操作，必须synchronized。因为x++不是原子操作
    public synchronized int increment() {
        return value++;
    }

    @Override
    public String toString() {
        return "CheesyCounter{" +
                "value=" + value +
                '}';
    }

    public static void main(String[] args) {
        CheesyCounter counter = new CheesyCounter();
        Runnable read = () -> {
            ThreadUtil.sleep(500);
            System.out.println(Thread.currentThread().getName() + "\t" + counter.getValue());
        };
        Runnable write = () -> {
            ThreadUtil.sleep(500);
            System.out.println(Thread.currentThread().getName() + "\t" + counter.increment());
        };

        for (int i = 0; i < 100; i++) {
            new Thread(read, "Read-Thread-" + i).start();
            new Thread(write, "write-Thread-" + i).start();
        }
        ThreadUtil.sleep(3000);
        System.out.println(counter);
    }
}
