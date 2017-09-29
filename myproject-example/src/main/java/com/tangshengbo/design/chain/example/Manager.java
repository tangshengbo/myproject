package com.tangshengbo.design.chain.example;

/**
 * Created by Tangshengbo on 2017/9/29.
 */
public abstract class Manager {

    protected String name;

    protected Manager superior;

    public Manager(String name) {
        this.name = name;
    }

    /**
     * 设置上一级
     * @param manger
     */
    protected void setSuperior(Manager manger) {
        this.superior = manger;
    }

    protected abstract void requestApplication(Request request);
}
