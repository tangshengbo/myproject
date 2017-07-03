package com.tangshengbo.tutorial.thread;

/**
 * Created by TangShengBo on 2017-07-02.
 */
public class ProducerConsumerTest {

    public static void main(String[] args) {
        BreadStack box = new BreadStack();
        Producer producer = new Producer(box);
        Consumer consumer = new Consumer(box);

        Thread producerThread = new Thread(producer, "QueueProducer");
        Thread consumerThread = new Thread(consumer, "ConsumerT1");
        Thread consumerThread2 = new Thread(consumer, "ConsumerT2");

        producerThread.start();
        consumerThread.start();
        consumerThread2.start();
    }
}
