package com.tangshengbo.jms.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Tang on 2017/6/30.
 */
public class JmsTemplate {

    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageConsumer consumer;
    private MessageProducer producer;
    private String queueName;
    private JmsMessageMode  jmsMessageMode = JmsMessageMode.QUEUE;

    public JmsTemplate(String queueName) {
        this(queueName, JmsMessageMode.QUEUE);
    }

    public JmsTemplate(String queueName, JmsMessageMode jmsMessageMode) {
        this.queueName = queueName;
        this.jmsMessageMode = jmsMessageMode;
        init();
    }

    public JmsTemplate(JmsMessageMode jmsMessageMode) {
        this("default-queue", jmsMessageMode);
    }
    public JmsTemplate() {
        this("default-queue");
    }

    private void init() {
        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,
                ActiveMQConnection.DEFAULT_BROKER_URL);
        try {
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            //第一个参数是是否是事务型消息，设置为true,第二个参数无效
            //第二个参数是
            //Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。异常也会确认消息，应该是在执行之前确认的
            //Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会删除消息。可以在失败的
            //时候不确认消息,不确认的话不会移出队列，一直存在，下次启动继续接受。接收消息的连接不断开，其他的消费者也不会接受（正常情况下队列模式不存在其他消费者）
            //DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。在需要考虑资源使用时，这种模式非常有效。
            session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
            if (JmsMessageMode.QUEUE == jmsMessageMode) {
                destination = session.createQueue(queueName);
            } else {
                destination = session.createTopic(queueName);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void send() {
        try {
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            producer.setTimeToLive(1000);//消息的存活时间
            producer.setPriority(9);//消息优先级
            MessageObject message = new MessageObject();
            for (int i = 0; i < 5; i++) {
                message.setId(i);
                message.setName("唐声波");
                producer.send(session.createObjectMessage(message));
            }
            System.out.println("消息已发送完成...........");

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void receive() {
        try {
            consumer = session.createConsumer(destination);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    ObjectMessage objectMessage = (ObjectMessage) message;
                    try {
                        MessageObject object = (MessageObject)objectMessage.getObject();
                        System.out.println("接收到:" + object.toString() + Thread.currentThread().getName());
                        //确认收到消息
                        message.acknowledge();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
