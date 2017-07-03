package com.tangshengbo.jms.rabbitmq;

import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Tang on 2017/7/3.
 */
public class QueueProducer extends EndPoint {

    public QueueProducer(String endpointName) throws IOException {
        super(endpointName);
    }

    public void sendMessage(Serializable object) throws IOException {
        channel.basicPublish("", endPointName, null, SerializationUtils.serialize(object));
    }

}
