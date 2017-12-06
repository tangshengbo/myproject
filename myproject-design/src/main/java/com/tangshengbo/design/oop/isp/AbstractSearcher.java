package com.tangshengbo.design.oop.isp;

/**
 * Created by TangShengBo on 2017-10-22.
 */
public abstract class AbstractSearcher {

    protected GoodBodyPettyGirl goodBodyPettyGirl;

    protected GreatTemperament greatTemperament;

    public AbstractSearcher(GreatTemperament greatTemperament) {
        this.greatTemperament = greatTemperament;
    }

    public AbstractSearcher(GoodBodyPettyGirl goodBodyPettyGirl) {
        this.goodBodyPettyGirl = goodBodyPettyGirl;
    }

    public abstract void show();
}
