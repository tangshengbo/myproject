package com.tangshengbo.scheduled;

import com.tangshengbo.jms.activemq.JmsTemplate;

/**
 * Created by Tang on 2017/6/30.
 */
public class CallbackTest {

    public static void main(String[] args) {
        MyTemplate template = new MyTemplate();
        String result = template.execute(new MyCallback<String>() {
            @Override
            public String doExecutor(JmsTemplate jmsTemplate) throws Exception {
                jmsTemplate.send();
                jmsTemplate.receive();
                return "success......";
            }
        });
        System.out.println(result);
    }
}
