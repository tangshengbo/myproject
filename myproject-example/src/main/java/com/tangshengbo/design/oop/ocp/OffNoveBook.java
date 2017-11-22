package com.tangshengbo.design.oop.ocp;

/**
 * Created by TangShengBo on 2017-10-22.
 */
public class OffNoveBook extends NovelBook {

    public OffNoveBook(String name, int price, String author) {
        super(name, price, author);
    }

    @Override
    public int getPrice() {
        int selPrice = super.getPrice();
        int offPrice = 0;
        if (selPrice > 4000) {
            offPrice = selPrice * 90 / 100;
        } else {
            offPrice = selPrice * 80 / 100;
        }
        return offPrice;
    }
}
