package com.tangshengbo.design.state.example;

/**
 * Created by TangShengBo on 2017-09-27.
 */
public class ConcreteStateA implements State {

    @Override
    public void handle(Context context) {
        context.setState(new ConcreteStateB());
    }
}
