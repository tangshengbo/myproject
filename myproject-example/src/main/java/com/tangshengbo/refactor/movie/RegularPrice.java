package com.tangshengbo.refactor.movie;

/**
 * Created by TangShengBo on 2017-03-28.
 */
public class RegularPrice extends Price {

    @Override
    public int getPriceCode() {
        return Movie.REGULAR;
    }

    @Override
    public double getCharge(int dayRented) {
        double result = 0;
        result += 2;
        if (dayRented > 2) {
            result += (dayRented - 2) * 1.5;
        }
        return result;
    }
}
