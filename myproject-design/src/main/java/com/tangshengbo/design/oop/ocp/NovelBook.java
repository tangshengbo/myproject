package com.tangshengbo.design.oop.ocp;

/**
 * Created by TangShengBo on 2017-10-22.
 */
public class NovelBook extends AbstractBook implements Book {

    public NovelBook(String name, int price, String author) {
        super(name, price, author);
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
}
