package com.tangshengbo.thread.queue;

import jodd.util.ThreadUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by TangShengBo on 2017-11-19.
 */
public class ProducerConsumerQueueTest {

    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1024);
        queue = new LinkedBlockingQueue<>(1024);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        new Thread(producer).start();
        new Thread(consumer).start();
        ThreadUtil.sleep(4000);
    }
}
