package com.tangshengbo.thread.queue;

import org.apache.commons.lang.exception.ExceptionUtils;

import java.util.concurrent.BlockingQueue;

/**
 * Created by TangShengBo on 2017-11-19.
 */
public class Consumer implements Runnable {

    protected BlockingQueue queue = null;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
    }
}
