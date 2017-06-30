package com.tangshengbo.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * Created by Tang on 2017/6/29.
 */
public class ScheduledExecutorTest {

    public static void main(String[] args) throws Exception {
        //线程池能按时间计划来执行任务，允许用户设定计划执行任务的时间，int类型的参数是设定
        //线程池中线程的最小数目。当任务较多时，线程池可能会自动创建更多的工作线程来执行任务
        //此处用Executors.newSingleThreadScheduledExecutor()更佳。
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(100);

        ScheduledFuture scheduledFuture = executorService.scheduleWithFixedDelay(new MyScheduleExecutor("Job1"), 0, 1, TimeUnit.SECONDS);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");//小写的mm表示的是分钟
        String customDateStr ="2017-6-29 17:27";
        Date customDate =sdf.parse(customDateStr);
        System.out.println(customDate.getTime() + "\t" + System.currentTimeMillis());
        executorService.scheduleAtFixedRate(new MyScheduleExecutor("Job2"), 0, 1, TimeUnit.SECONDS);
          executorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(scheduledFuture.isDone() + "\t" + scheduledFuture.isCancelled());
                scheduledFuture.cancel(true);
                //System.exit(0);
            }
        }, customDate.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }
}
