package com.tangshengbo.refactor.movie;

/**
 * Created by TangShengBo on 2017-03-28.
 */
public class NewReleasePrice extends Price {

    @Override
    public int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    @Override
    public double getCharge(int dayRented) {
       return dayRented * 3;
    }

    @Override
    public int getFrequentRenterPoints(int dayRented) {
        return (dayRented > 1) ? 2 : 1;
    }
}
