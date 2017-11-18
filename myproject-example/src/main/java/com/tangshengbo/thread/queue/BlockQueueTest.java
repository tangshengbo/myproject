package com.tangshengbo.thread.queue;

import java.util.concurrent.*;

/**
 * Created by TangShengBo on 2017-11-15.
 */
public class BlockQueueTest {

    public static void main(String[] args) {
        arrayBlockingQueue();
        linkedBlockingQueue();
        priorityBlockingQueue();
        delayQueue();
        synchronousQueue();
        linkedTransferQueue();
        linkedBlockingDeque();
    }

    private static void arrayBlockingQueue() {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(100);
        arrayBlockingQueue.add("ArrayBlockingQueue");
        System.out.println(arrayBlockingQueue.peek());
    }

    private static void linkedBlockingQueue() {
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
        linkedBlockingQueue.add("LinkedBlockingQueue");
        System.out.println(linkedBlockingQueue.peek());
    }

    private static void priorityBlockingQueue() {
        PriorityBlockingQueue<String> priorityBlockingQueue = new PriorityBlockingQueue<>(20);
        priorityBlockingQueue.offer("PriorityBlockingQueue");
        System.out.println(priorityBlockingQueue.peek());
    }

    private static void delayQueue() {
        DelayQueue delayQueue = new DelayQueue();
        System.out.println(delayQueue.add(new Delayed() {
            @Override
            public long getDelay(TimeUnit unit) {
                return 100;
            }

            @Override
            public int compareTo(Delayed o) {
                return 0;
            }
        }));
        System.out.println(delayQueue.peek().getDelay(TimeUnit.SECONDS));
    }

    private static void synchronousQueue() {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
        synchronousQueue.offer("SynchronousQueue");
        System.out.println(synchronousQueue.peek());
    }

    private static void linkedTransferQueue() {
        LinkedTransferQueue<String> linkedTransferQueue = new LinkedTransferQueue<>();
        try {
            linkedTransferQueue.tryTransfer("LinkedTransferQueue", 1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(linkedTransferQueue.poll() + "-->");
    }

    private static void linkedBlockingDeque() {
        LinkedBlockingDeque<String> linkedBlockingDeque = new LinkedBlockingDeque<>();
        try {
            linkedBlockingDeque.putLast("linkedBlockingDeque");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(linkedBlockingDeque.peekLast());
    }
}
