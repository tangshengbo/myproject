package com.tangshengbo.thread.forkjoin;


import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownLatchTest {


    private static Integer sum = 0;
    private static Integer n = 100;

    private final static Integer latchSize = 5;

    private final static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(latchSize);

    private static CountDownLatch latch;
    private final static Lock LOCK = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start("");
        latch = new CountDownLatch(latchSize);
        int avg = n / latchSize;
        int rem = n % latchSize;
        int left, right;
        for (int i = 1; i <= latchSize; i++) {
            left = (i - 1) * avg + 1;
            right = i == latchSize ? (i * avg + rem) : (i * avg);
            fixedThreadPool.execute(new Run(left, right));
        }
        latch.await(); // 等待10个进程完全结束，在进行主线程
        System.out.println("总计:" + Thread.currentThread().getName() + "--> sum:" + sum);

    }


    static class Run implements Runnable {
        private int left;
        private int right;

        public Run(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public void run() {
            int res = 0;
            for (int i = left; i <= right; i++) {
                res += i;
            }

            LOCK.lock(); // static sum 并发相加 加锁
            try {
                sum += res;
                System.out.println("已完成" + Thread.currentThread().getName() + "--> left:" + left + ", right:" + right + ", res:" + res + ", sum: " + sum);
                latch.countDown(); // 完成一个 减去一个
            } finally {
                LOCK.unlock();
            }

        }

    }
}
