package com.tangshengbo.design.oop.ocp;

/**
 * Created by TangShengBo on 2017-10-22.
 */
public class ComputerBookImpl extends AbstractBook implements ComputerBook {

    private String scope;

    public ComputerBookImpl(String name, int price, String author, String scope) {
        super(name, price, author);
        this.scope = scope;
    }

    @Override
    public String getScope() {
        return this.scope;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }

    @Override
    public String toString() {
        return super.toString() + " 分类:" + this.scope;
    }
}
