package com.tangshengbo.design.proxy.pursuer;

/**
 * Created by Tangshengbo on 2017/10/11.
 */
public class Pursuer implements SendGift {

    private String name;

    private SchoolGirl schoolGirl;

    public Pursuer(String name, SchoolGirl schoolGirl) {
        this.name = name;
        this.schoolGirl = schoolGirl;
    }

    public void sendFlower() {
        System.out.println(String.format("%s 送你花", schoolGirl.getName()));
    }

    public void sendChocolate() {
        System.out.println(String.format("%s 送你巧克力", schoolGirl.getName()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
