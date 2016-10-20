package com.tangshengbo.rabbit.test.two;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang.SerializationUtils;

public class Sender extends BaseConnector {
	public Sender(String queueName) throws IOException, TimeoutException {
		super(queueName);
	}
	
	public void sendMessage(Serializable object) throws IOException {
        channel.basicPublish("",queueName, null, SerializationUtils.serialize(object));
    }  	
}
