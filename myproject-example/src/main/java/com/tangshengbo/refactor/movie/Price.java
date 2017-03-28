package com.tangshengbo.refactor.movie;

/**
 * Created by TangShengBo on 2017-03-28.
 */
public abstract class Price {

    public abstract int getPriceCode();

    public abstract double getCharge(int dayRented);

    public int getFrequentRenterPoints(int dayRented) {
       return 1;
    }
}
