package com.tangshengbo.refactor.movie;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by TangShengBo on 2017-03-27.
 */
public class Customer {

    /**
     * @param name
     */
    public Customer(String name) {
        super();
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    private Vector rentals = new Vector();

    public void addRental(Rental arg) {
        rentals.addElement(arg);
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentalss = rentals.elements();
        String result = "Rental Record for" + " " + getName() + "\n";
        double thisAmount;
        while (rentalss.hasMoreElements()) {
            Rental each = (Rental) rentalss.nextElement();
            thisAmount = each.getCharage();
            //积分  每借一张加1个积分

            //积分累加条件  新版本的片子，借的时间大于1天
            frequentRenterPoints += each.getFrequentRenterPoints();
            result += "\t" + each.getMovie().getTitle() + "\t"
                    + String.valueOf(thisAmount) + "\n";

            totalAmount += thisAmount;
        }

        result += "Amount owed is " + getTotalCharge() + "\n";
        result += "You earned " + getFrequentRenterPoints() + " "
                + "frequent renter points";
        return result;
    }

    private double amountFor(Rental rental) {
        return rental.getCharage();
    }

    private double getTotalCharge() {
        double result = 0;
        Enumeration elements = rentals.elements();
        while (elements.hasMoreElements()) {
            Rental rental = (Rental) elements.nextElement();
            result += rental.getCharage();
        }
        return result;
    }

    private int getFrequentRenterPoints() {
        int frequentRenterPoints = 0;
        Enumeration elements = rentals.elements();
        while (elements.hasMoreElements()) {
            Rental rental = (Rental) elements.nextElement();
            frequentRenterPoints += rental.getFrequentRenterPoints();
        }
        return frequentRenterPoints;
    }



}
