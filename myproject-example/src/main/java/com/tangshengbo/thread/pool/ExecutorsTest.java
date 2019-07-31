package com.tangshengbo.thread.pool;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tangshengbo on 2017/11/14.
 */
public class ExecutorsTest {

    public static void main(String[] args) {
        createThreadPoolExecutor();
        createScheduledThreadPoolExecutor();
        System.out.println(UUID.randomUUID().toString());
    }

    private static void createThreadPoolExecutor() {
        System.out.println("===============ThreadPoolExecutor start===============");
        //FixedThreadPool适用于为了满足资源管理的需求，而需要限制当前线程数量的应用场
        //景，它适用于负载比较重的服务器。
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        //SingleThreadExecutor适用于需要保证顺序地执行各个任务；并且在任意时间点，不会有多
        //个线程是活动的应用场景。
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        //CachedThreadPool是大小无界的线程池，适用于执行很多的短期异步任务的小程序，或者
        //是负载较轻的服务器。
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        System.out.println("===============ThreadPoolExecutor end===============");
    }

    private static void createScheduledThreadPoolExecutor() {
        System.out.println("===============ScheduledThreadPoolExecutor start===============");
        //ScheduledThreadPoolExecutor适用于需要多个后台线程执行周期任务，同时为了满足资源
        //管理的需求而需要限制后台线程的数量的应用场景
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(100);

        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟三秒");
            }
        }, 1,3, TimeUnit.SECONDS);

        //SingleThreadScheduledExecutor适用于需要单个后台线程执行周期任务，同时需要保证顺
        //序地执行各个任务的应用场景
        ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        System.out.println("===============ScheduledThreadPoolExecutor end===============");
    }
}
