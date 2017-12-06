package com.tangshengbo.design.memento;

/**
 * Created by TangShengBo on 2017-10-16.
 */
public class Memento {

    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
