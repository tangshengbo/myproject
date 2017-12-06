package com.tangshengbo.design.facade.fund;

/**
 * Created by Tangshengbo on 2017/9/30.
 */
public abstract class AbstractStock implements Stock {

    protected String name;

    public AbstractStock(String name) {
        this.name = name;
    }
}
