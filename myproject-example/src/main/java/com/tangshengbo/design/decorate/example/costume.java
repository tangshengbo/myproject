package com.tangshengbo.design.decorate.example;

/**
 * Created by TangShengBo on 2017-09-24.
 */
public abstract class Costume extends Person {

    private Person component;

    protected void decorate(Person component) {
        this.component = component;
    }
    @Override
    public void show() {
        if (component != null) {
            component.show();
        }
    }
}
