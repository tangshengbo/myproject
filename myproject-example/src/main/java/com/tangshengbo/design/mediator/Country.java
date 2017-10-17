package com.tangshengbo.design.mediator;

/**
 * Created by Tangshengbo on 2017/10/17.
 */
public abstract class Country {

    protected UnitedNations mediator;

    public Country(UnitedNations mediator) {
        this.mediator = mediator;
    }

    public abstract void declare(String message);

    public abstract void getMessage(String message);
}
