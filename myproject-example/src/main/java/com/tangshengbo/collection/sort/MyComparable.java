package com.tangshengbo.collection.sort;

import com.google.common.util.concurrent.RateLimiter;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * Created by tangshengbo on 2017/3/23.
 */
public class MyComparable implements Comparable<MyComparable> {

    private int count;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyComparable(int count) {
        this.count = count;
    }

    public MyComparable() {
    }

    public MyComparable(int count, String name) {
        this.count = count;
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int compareTo(MyComparable o) {
        return this.getCount() - o.getCount();
    }

    @Override
    public String toString() {
        return "MyComparable{" +
                "count=" + count +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) throws Exception {
//        List<MyComparable> comparables = new ArrayList<>();
//        MyComparable comparable2 = new MyComparable(2, "a");
//        MyComparable comparable = new MyComparable(1, "z");
//        MyComparable comparable5 = new MyComparable(5, "a");
//        MyComparable comparable3 = new MyComparable(3, "l");
//        MyComparable comparable4 = new MyComparable(4, "v");
//        SoftReference<MyComparable> softReference = new SoftReference<>(comparable2);
//
//        comparables.add(comparable);
//        comparables.add(comparable4);
//        comparables.add(comparable3);
//        comparables.add(comparable2);
//        comparables.add(comparable5);
//        List<MyComparable> collect = comparables.stream()
//                .sorted(Comparator.comparing(MyComparable::getName).reversed()).collect(Collectors.toList());
//        for (MyComparable myComparable : collect) {
//            System.out.println(myComparable);
//        }
//
//        comparables.parallelStream().forEach(x -> {
//            System.out.println(x);
//
//        });
//        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
//
//        readWriteLock.readLock().lock();
//
//        readWriteLock.writeLock().lock();


        MyComparable gr = new MyComparable();
        Instant start = Instant.now();
        Thread t1 = new Thread(()->gr.handlerWithLimiter(), "A01");
        t1.start();
        Thread t2 = new Thread(()->gr.handlerWithLimiter(), "A02");
        t2.start();
        Thread t3 = new Thread(()->gr.handlerWithLimiter(), "A03");
        t3.start();
        Thread t4 = new Thread(()->gr.handlerWithLimiter(), "A04");
        t4.start();
        Thread t5 = new Thread(()->gr.handlerWithLimiter(), "A05");
        t5.start();
        Thread t6 = new Thread(()->gr.handlerWithLimiter(), "A06");
        t6.start();
        Thread t7 = new Thread(()->gr.handlerWithLimiter(), "A07");
        t7.start();
        Thread t8 = new Thread(()->gr.handlerWithLimiter(), "A08");
        t8.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
        t7.join();
        t8.join();
        System.out.println("It costs "+ Duration.between(start, Instant.now()));
    }


    final RateLimiter limit = RateLimiter.create(100);//10ms就有一个令牌

    public void handlerWithLimiter() {
        boolean hasToken = limit.tryAcquire(5, TimeUnit.MILLISECONDS);//尝试获得令牌,允许最大等待时间5ms内获得令牌
        if (hasToken) {
            System.out.println(Thread.currentThread().getName() + " has token to do something...");
        } else {
            System.out.println(Thread.currentThread().getName() + " has not get the Token!!!!");
        }


    }
}
