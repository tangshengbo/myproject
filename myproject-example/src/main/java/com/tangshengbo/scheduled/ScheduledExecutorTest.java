package com.tangshengbo.scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tang on 2017/6/29.
 */
public class ScheduledExecutorTest {


    public static void main(String[] args) throws Exception {
        //线程池能按时间计划来执行任务，允许用户设定计划执行任务的时间，int类型的参数是设定
        //线程池中线程的最小数目。当任务较多时，线程池可能会自动创建更多的工作线程来执行任务
        //此处用Executors.newSingleThreadScheduledExecutor()更佳。
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);

        ScheduledFuture scheduledFuture = executorService.scheduleAtFixedRate(new MyScheduleExecutor("Job1"), 2, 1, TimeUnit.SECONDS);
        System.out.println(scheduledFuture.getDelay(TimeUnit.SECONDS));
        System.out.println(scheduledFuture.get() + "lll");
        TimeUnit.SECONDS.sleep(10);
        if (scheduledFuture.isDone() && !scheduledFuture.isCancelled()) {
            System.out.println(scheduledFuture.get());
        }
        executorService.scheduleWithFixedDelay(new MyScheduleExecutor("Job2"),5, 2, TimeUnit.SECONDS);
    }
}
