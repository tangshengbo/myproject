package com.tangshengbo.service;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/12/8.
 */
@Service("longTermTaskCallbackService")
public class LongTermTaskCallbackServiceImpl {

    private static final int COREPOOLSIZE = 4;

    private static final long NEEDSECONDS = 3;

    //private Random random = new Random();

   // private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(COREPOOLSIZE);

    /*public void makeRemoteCallAndUnknownWhenFinish(LongTermTaskCallbackService callback){
        System.out.println("完成此任务需要 : " + NeedSeconds + " 秒");

        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                callback.callback("长时间异步调用完成.");
            }
        },NeedSeconds, TimeUnit.SECONDS);
    }*/
}
