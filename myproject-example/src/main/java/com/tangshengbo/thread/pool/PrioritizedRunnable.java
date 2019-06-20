package com.tangshengbo.thread.pool;


import jodd.util.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PrioritizedRunnable implements Runnable, Comparable<PrioritizedRunnable> {

    private static final Logger log = LoggerFactory.getLogger(PrioritizedRunnable.class);

    private long rts;
    private String name;

    PrioritizedRunnable(long rts, String name) {
        this.rts = rts;
        this.name = name;
    }

    public long getRts() {
        return rts;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(PrioritizedRunnable secondOne) {
        // 时间越小越优先
        log.info("compareTo. this.name={}, secondOne.name={}", this.getName(), secondOne.getName());
        if (this.getRts() < secondOne.getRts()) {
            return -1;
        } else if (this.getRts() > secondOne.getRts()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void run() {
        Random random = new Random();
        log.info("rts={}, name={}", rts, name);
        try {
            int sleepRandom = random.nextInt(200);
            Thread.sleep(sleepRandom);
        } catch (Exception ex) {
            log.info("sleep exception", ex);
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                1,
                Long.MAX_VALUE, /* timeout */
                TimeUnit.NANOSECONDS,
                new PriorityBlockingQueue<>(10),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        PrioritizedRunnable p1 = new PrioritizedRunnable(1234, "name1-1");
        PrioritizedRunnable p2 = new PrioritizedRunnable(1500, "name4-2");
        PrioritizedRunnable p3 = new PrioritizedRunnable(1590, "name5-3");
        PrioritizedRunnable p4 = new PrioritizedRunnable(1490, "name3-4");
        PrioritizedRunnable p5 = new PrioritizedRunnable(1290, "name2-5");

        executor.execute(p1);
        executor.execute(p2);
        executor.execute(p3);
        executor.execute(p4);
        executor.execute(p5);
        log.info("submit 5 Runnable");
        ThreadUtil.sleep(30 * 1000);
        executor.shutdown();
        log.info("done!");
    }

}
