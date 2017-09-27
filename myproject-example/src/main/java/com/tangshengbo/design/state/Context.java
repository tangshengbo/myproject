package com.tangshengbo.design.state;

/**
 * Created by Tangshengbo on 2017/9/27.
 */
public class Context {

    private State state;

    public Context(){
        state = null;
    }

    public void setState(State state){
        this.state = state;
    }

    public State getState(){
        return state;
    }
}
