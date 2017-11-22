package com.tangshengbo.design.oop.isp;

/**
 * Created by TangShengBo on 2017-10-22.
 */
public class PettyGirlImpl implements GoodBodyPettyGirl, GreatTemperament {

    private String name;

    public PettyGirlImpl(String name) {
        this.name = name;
    }

    @Override
    public void goodLooking() {
        System.out.println(String.format("%s ----脸蛋漂亮", name));
    }

    @Override
    public void niceFigure() {
        System.out.println(String.format("%s ----身材好", name));
    }

    @Override
    public void greatTemperament() {
        System.out.println(String.format("%s ----气质好", name));
    }
}
