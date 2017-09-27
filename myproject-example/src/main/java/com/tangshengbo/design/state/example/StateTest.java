package com.tangshengbo.design.state.example;

/**
 * Created by TangShengBo on 2017-09-27.
 */
public class StateTest {

    public static void main(String[] args) {
        Context context = new Context(new ConcreteStateA());
        context.request();
        context.request();
        context.request();
        context.request();
    }
}
