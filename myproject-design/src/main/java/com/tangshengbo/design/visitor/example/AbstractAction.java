package com.tangshengbo.design.visitor.example;

/**
 * Created by Tangshengbo on 2017/10/18.
 */
public abstract class AbstractAction {

    private String name;

    public AbstractAction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
