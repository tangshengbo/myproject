package com.tangshengbo.refactor.movie;

/**
 * Created by TangShengBo on 2017-03-27.
 */
public class Movie {

    /**
     * @param title
     * @param priceCode
     */
    public Movie(String title, int priceCode) {
        super();
        this.title = title;
        setPriceCode(priceCode);
    }

    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String title;
    private Price price;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriceCode() {
        return price.getPriceCode();
    }

    public void setPriceCode(int arg) {
        switch (arg) {
            case REGULAR:
                price = new RegularPrice();
                break;
            case NEW_RELEASE:
                price = new NewReleasePrice();
                break;
            case CHILDRENS:
                price = new ChildrensPrice();
                break;
            default:
                throw new IllegalArgumentException("非法参数");
        }
    }

    public double getCharge(int dayRented) {
        return price.getCharge(dayRented);
    }

    public int getFrequentRenterPoints(int dayRented) {
        return price.getFrequentRenterPoints(dayRented);
    }

}
