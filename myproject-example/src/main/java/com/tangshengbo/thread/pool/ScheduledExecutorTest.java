package com.tangshengbo.thread.pool;

import java.util.concurrent.*;

/**
 * Created by Tang on 2017/6/29.
 */
public class ScheduledExecutorTest {

    private final static ScheduledExecutorService EXECUTOR_SERVICE = Executors.newScheduledThreadPool(10);

    public static void main(String[] args) throws Exception {
        //线程池能按时间计划来执行任务，允许用户设定计划执行任务的时间，int类型的参数是设定
        //线程池中线程的最小数目。当任务较多时，线程池可能会自动创建更多的工作线程来执行任务
        //此处用Executors.newSingleThreadScheduledExecutor()更佳。
        scheduleWithFixedDelay();
        scheduleAtFixedRate();
        schedule();
        submit();
    }

    private static void scheduleWithFixedDelay() {
        ScheduledFuture scheduledFuture = EXECUTOR_SERVICE.scheduleWithFixedDelay(new MyScheduleExecutor("JobA"), 1, 1, TimeUnit.SECONDS);
        System.out.println(scheduledFuture.getDelay(TimeUnit.SECONDS));
    }

    private static void scheduleAtFixedRate() {
        ScheduledFuture scheduledFuture = EXECUTOR_SERVICE.scheduleAtFixedRate(new MyScheduleExecutor("JobB"), 1, 2, TimeUnit.SECONDS);
        System.out.println(scheduledFuture.getDelay(TimeUnit.SECONDS));
    }

    private static void schedule() {
        ScheduledFuture scheduledFuture = EXECUTOR_SERVICE.schedule(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Callable";
            }
        }, 1, TimeUnit.SECONDS);
        try {
            System.out.println(scheduledFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    private static void submit() {
        Future<String> future = EXECUTOR_SERVICE.submit(new MyScheduleExecutor("JobC"), "success");
        try {
            System.out.println(future.get());
            future.cancel(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
