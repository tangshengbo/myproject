package com.tangshengbo.jms.activemq;

import java.util.concurrent.TimeUnit;

/**
 * Created by Tang on 2017/6/30.
 */
public class ActiveMQTest {

    public static void main(String[] args) throws InterruptedException {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.send();
        TimeUnit.SECONDS.sleep(2);
//        int i = 1 / 0;
        jmsTemplate.receive();

    }
}
