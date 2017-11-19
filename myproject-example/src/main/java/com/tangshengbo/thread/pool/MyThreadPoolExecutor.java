package com.tangshengbo.thread.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tangshengbo on 2017/11/14.
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor {

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println("===================beforeExecute:==============");
        System.out.println(t.getName() + "开始执行");
        printThreadPoolInfo();
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        System.out.println("===================afterExecute:==============");
        printThreadPoolInfo();
    }

    @Override
    protected void terminated() {
        System.out.println("===================terminated:==============");
        printThreadPoolInfo();
    }

    private void printThreadPoolInfo() {
        System.out.println("getCompletedTaskCount:" + this.getCompletedTaskCount());
        System.out.println("getActiveCount:" + this.getActiveCount());
        System.out.println("getTaskCount:" + this.getTaskCount());
        System.out.println("getPoolSize:" + this.getPoolSize());
        System.out.println("getLargestPoolSize:" + this.getLargestPoolSize());
    }
}
