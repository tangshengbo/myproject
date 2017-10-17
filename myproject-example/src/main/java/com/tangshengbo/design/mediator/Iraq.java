package com.tangshengbo.design.mediator;

/**
 * Created by Tangshengbo on 2017/10/17.
 */
public class Iraq extends Country {

    public Iraq(UnitedNations mediator) {
        super(mediator);
    }

    @Override
    public void declare(String message) {
        mediator.declare(message, this);
    }

    @Override
    public void getMessage(String message) {
        System.out.println(String.format("伊拉克获得对方消息：%s", message));
    }
}
