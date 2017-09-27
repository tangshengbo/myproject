package com.tangshengbo.design.state;

/**
 * Created by Tangshengbo on 2017/9/27.
 */
public class StartState implements State {

    @Override
    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    public String toString() {
        return "Start State";
    }
}
