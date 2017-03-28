package com.tangshengbo.refactor.movie;

/**
 * Created by TangShengBo on 2017-03-27.
 */
public class Rental {

    private int dayRented;

    private Movie movie;

    /**
     * @param movie
     * @param dayRented
     */
    public Rental(Movie movie, int dayRented) {
        super();
        this.movie = movie;
        this.dayRented = dayRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDayRented() {
        return dayRented;
    }

    public double getCharage() {
       return movie.getCharge(dayRented);
    }

    public int getFrequentRenterPoints() {
        return movie.getFrequentRenterPoints(dayRented);
    }
}
