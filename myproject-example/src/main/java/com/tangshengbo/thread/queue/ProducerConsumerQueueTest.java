package com.tangshengbo.thread.queue;

import java.util.concurrent.SynchronousQueue;

/**
 * Created by TangShengBo on 2017-11-19.
 */
public class ProducerConsumerQueueTest {

    public static void main(String[] args) {
//        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
////        queue = new LinkedBlockingQueue<>(1024);
//        Producer producer = new Producer(queue);
//        Consumer consumer = new Consumer(queue);
//        new Thread(producer).start();
//        new Thread(consumer).start();
//        ThreadUtil.sleep(4000);
//
//        DelayQueue<MyDelayed> delayQueue = new DelayQueue<>();
//        delayQueue.add(new MyDelayed("delay1", 100000));
//        delayQueue.add(new MyDelayed("delay2", 3000));
//        delayQueue.add(new MyDelayed("delay3", 1000));
//        try {
//
//            for (int i = 0; i < 3; i++) {
//                System.out.println(delayQueue.take());
//            }
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        try {
            SynchronousQueue synchronousQueue = new SynchronousQueue();
//            System.out.println(synchronousQueue.take());
            Runnable r = () -> {
//                for (;;) {

                    try {
                        System.out.println(synchronousQueue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                }
            };
            new Thread(r).start();
            synchronousQueue.put("xxx");
//            synchronousQueue.put("xxx");

//            ThreadUtil.sleep(10000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
