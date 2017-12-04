package com.tangshengbo.thread.scheduled;

/**
 * Created by Tang on 2017/6/30.
 */
public class CallbackTest {

    public static void main(String[] args) {
        MyTemplate template = new MyTemplate();
        String result = template.execute(jmsTemplate -> {
            jmsTemplate.send();
            jmsTemplate.receive();
            return "success......";
        });
        System.out.println(result);
    }
}
