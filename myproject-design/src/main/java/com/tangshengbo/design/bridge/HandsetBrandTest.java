package com.tangshengbo.design.bridge;

/**
 * Created by Tangshengbo on 2017/10/10.
 */
public class HandsetBrandTest {

    public static void main(String[] args) {
        HandsetBrand handsetBrand;
        handsetBrand = new HandsetBrandAGame();
        handsetBrand.run();

        handsetBrand = new HandsetBrandBGame();
        handsetBrand.run();

        handsetBrand = new HandsetBrandAMusic();
        handsetBrand.run();

        handsetBrand = new HandsetBrandBMusic();
        handsetBrand.run();
    }
}
