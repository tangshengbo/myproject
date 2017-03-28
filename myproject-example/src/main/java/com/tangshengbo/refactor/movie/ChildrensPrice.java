package com.tangshengbo.refactor.movie;

/**
 * Created by TangShengBo on 2017-03-28.
 */
public class ChildrensPrice extends Price {

    @Override
    public int getPriceCode() {
        return Movie.CHILDRENS;
    }

    @Override
    public double getCharge(int dayRented) {
        double result = 0;
        result += 1.5;
        if (dayRented > 3) {
            result += (dayRented - 3) * 1.5;
        }
        return result;
    }

}
