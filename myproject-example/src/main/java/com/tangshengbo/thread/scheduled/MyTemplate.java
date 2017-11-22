package com.tangshengbo.thread.scheduled;

import com.tangshengbo.jms.activemq.JmsTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by Tang on 2017/6/30.
 */
public class MyTemplate {

    public <T> T execute(MyCallback<T> myCallback) {
        System.out.println("execute start.....................");
        T result = null;
        JmsTemplate jmsTemplate = null;
        try {
            TimeUnit.SECONDS.sleep(3);
            jmsTemplate = new JmsTemplate();
            result = myCallback.doExecutor(jmsTemplate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("execute end.....................");
        return result;
    }
}
