package com.tangshengbo.thread.queue;

import org.apache.commons.lang.exception.ExceptionUtils;

import java.util.concurrent.BlockingQueue;

/**
 * Created by TangShengBo on 2017-11-19.
 */
public class Producer implements Runnable {

    protected BlockingQueue<String> queue = null;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            queue.put("1");
            Thread.sleep(1000);
            queue.put("2");
            Thread.sleep(1000);
            queue.put("3");
        }catch(InterruptedException e){
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
    }
}

