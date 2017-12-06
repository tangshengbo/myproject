package com.tangshengbo.design.bridge.example;

/**
 * Created by Tangshengbo on 2017/10/10.
 */
public class HandsetBrandTest {

    public static void main(String[] args) {
        HandsetBrand handsetBrand = new HandsetBrandA(new HandsetSoftGame());
        handsetBrand.run();

        handsetBrand = new HandsetBrandB(new HandsetSoftGame());
        handsetBrand.run();

        handsetBrand = new HandsetBrandA(new HandsetSoftMusic());
        handsetBrand.run();

        handsetBrand = new HandsetBrandB(new HandsetSoftMusic());
        handsetBrand.run();

        handsetBrand = new HandsetBrandB(new HandsetSoftVideo());
        handsetBrand.run();

        handsetBrand = new HandsetBrandC(new HandsetSoftVideo());
        handsetBrand.run();

    }
}
