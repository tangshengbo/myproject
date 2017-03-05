package com.tangshengbo.design.prototype;

/**
 * Created by tangshengbo on 2017/3/5.
 */
public abstract class Shape implements Cloneable {

    private String id;

    protected String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract void draw();

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object clone  = super.clone();
        return clone;
    }
}
