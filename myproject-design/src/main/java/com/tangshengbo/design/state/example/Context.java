package com.tangshengbo.design.state.example;

/**
 * Created by TangShengBo on 2017-09-27.
 */
public class Context {

    private State state;

    public Context(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        System.out.println(String.format("当前状态: %s", this.getState().getClass().getName()));
    }

    public void request() {
        state.handle(this);
    }
}
