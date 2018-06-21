package com.tangshengbo.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2016/12/29.
 */
public class SchedulerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerTest.class);

    public static void main(String[] args) {

        //通过schedulerFactory获取一个调度器
        SchedulerFactory schedulerfactory = new StdSchedulerFactory();
        Scheduler scheduler = null;

        // 通过schedulerFactory获取一个调度器
        try {
            scheduler = schedulerfactory.getScheduler();

            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类
            JobDetail job = JobBuilder.newJob(HelloQuartz.class).withIdentity("JobName", "JobGroupName").build();
            job.getJobDataMap().put("name", "糖果");
            // 定义调度触发规则

            // SimpleTrigger
//            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("SimpleTrigger", Scheduler.DEFAULT_GROUP)
//                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1).withRepeatCount(1))
//                    .startNow().build();

            //  corn表达式  每五秒执行一次
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("CronTrigger1", "CronTriggerGroup")
                    .withSchedule(CronScheduleBuilder.cronSchedule("*/1 * * * * ?"))
                    .startNow().build();

            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(job, trigger);

            // 启动调度
            scheduler.start();

            Thread.sleep(10000);

            // 停止调度
            scheduler.shutdown();


        } catch (SchedulerException e) {

            LOGGER.info("SchedulerException {}", e.getMessage());

        } catch (InterruptedException e) {

            LOGGER.info("SchedulerException {}", e.getMessage());

        }

    }


}
