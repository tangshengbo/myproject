package com.tangshengbo.scheduled.callback;

/**
 * Created by TangShengBo on 2017-07-03.
 */
public class CallBackTest {

    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        teacher.publish(new Jack("Jack"));
    }
}
