package com.tangshengbo.design.proxy.pursuer;

/**
 * Created by Tangshengbo on 2017/10/11.
 */
public class PursuerProxy implements SendGift {

    private String name;

    private Pursuer pursuer;

    public PursuerProxy(String name, SchoolGirl schoolGirl) {
        this.name = name;
        this.pursuer = new Pursuer("唐唐", schoolGirl);
    }

    @Override
    public void sendFlower() {
        pursuer.sendFlower();
    }

    @Override
    public void sendChocolate() {
        pursuer.sendChocolate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
