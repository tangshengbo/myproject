package com.tangshengbo.design.composite;

/**
 * Created by Tangshengbo on 2017/10/13.
 */
public abstract class AbstractComponent implements Component {

    protected String name;

    public AbstractComponent(String name) {
        this.name = name;
    }
}
