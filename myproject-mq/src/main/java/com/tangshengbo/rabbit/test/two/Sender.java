package com.tangshengbo.rabbit.test.two;

import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

public class Sender extends BaseConnector {
	public Sender(String queueName) throws IOException, TimeoutException {
		super(queueName);
	}
	
	public void sendMessage(Serializable object) throws IOException {
        getChannel().basicPublish("",getQueueName(), null, SerializationUtils.serialize(object));
    }  	
}
