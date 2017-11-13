package com.tangshengbo.thread.pool;

import jodd.util.ThreadUtil;

import java.util.concurrent.*;

/**
 * Created by TangShengBo on 2017-11-13.
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 20, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " for thread pool");
            }
        });

        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " for thread pool");
                ThreadUtil.sleep(2000);
                return Thread.currentThread().getName();
            }
        });
        String result = future.get();
        System.out.println(result);
        executor.shutdown();
    }
}
