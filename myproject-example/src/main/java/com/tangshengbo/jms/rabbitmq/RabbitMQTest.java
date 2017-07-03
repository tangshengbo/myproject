package com.tangshengbo.jms.rabbitmq;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Tang on 2017/7/3.
 */
public class RabbitMQTest {

    public static void main(String[] args) {
        QueueProducer producer = null;
        try {
            QueueConsumer consumer = new QueueConsumer("queue");
            Thread consumerThread = new Thread(consumer);
            Thread consumerThread2 = new Thread(consumer);
            consumerThread.start();
            consumerThread2.start();
            producer = new QueueProducer("queue");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            HashMap message = new HashMap();
            message.put("message number", i);
            try {
                producer.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Message Number " + i + " sent.");
        }
    }
}
