package com.tangshengbo.thread.scheduled;

import com.tangshengbo.jms.activemq.JmsTemplate;

/**
 * Created by Tang on 2017/6/30.
 */
public interface MyCallback<T> {

    T doExecutor(JmsTemplate jmsTemplate) throws Exception;
}
