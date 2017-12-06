package com.tangshengbo.design.oop.ocp;

import java.text.NumberFormat;

/**
 * Created by TangShengBo on 2017-10-22.
 */
public abstract class AbstractBook implements Book {

    private static NumberFormat numberFormat = NumberFormat.getNumberInstance();

    static {
        numberFormat.setMinimumFractionDigits(2);
    }

    protected String name;

    protected int price;

    protected String author;

    public AbstractBook(String name, int price, String author) {
        this.name = name;
        this.price = price;
        this.author = author;
    }

    @Override
    public String toString() {
        return String.format("书籍名称:%s  书籍作者:%s 书籍价格:%s 元", this.getName(), this.getAuthor(),
                numberFormat.format(this.getPrice() / 100.0));
    }
}
