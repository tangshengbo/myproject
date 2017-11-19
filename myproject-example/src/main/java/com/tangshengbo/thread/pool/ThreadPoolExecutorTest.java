package com.tangshengbo.thread.pool;

import jodd.util.ThreadUtil;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by TangShengBo on 2017-11-13.
 */

public class ThreadPoolExecutorTest {

    private final static ThreadPoolExecutor executor = new MyThreadPoolExecutor(10, 20, 20, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),new ThreadFactory(){
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("MyThread-" + threadNumber.getAndIncrement());
            return thread;
        }
    });

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        Future futureTask = submit();
        Future<String> future = callable();
        String result = future.get();
        System.out.println("result: " + result + "\t" + futureTask.get());
        executor.shutdown();
    }

    private static Future<String> callable() {
        return executor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println(Thread.currentThread().getName() + " for thread pool");
                    ThreadUtil.sleep(1000);
                    return Thread.currentThread().getName();
                }
            });
    }

    private static Future submit() {
        String expectValue = "success";
        Callable<String> callable = Executors.callable(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " for thread pool");
            }
        }, expectValue);
        return executor.submit(callable);
    }
}
