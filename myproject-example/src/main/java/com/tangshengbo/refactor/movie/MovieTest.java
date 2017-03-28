package com.tangshengbo.refactor.movie;

/**
 * Created by TangShengBo on 2017-03-27.
 */
public class MovieTest {

    public static void main(String[] args) {
        Movie mov = new Movie("metal",1);
        Rental ren = new Rental(mov,8);
        Customer cus = new Customer("Lee");
        cus.addRental(ren);

        System.out.println(cus.statement());
    }
}
