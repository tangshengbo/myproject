package com.tangshengbo.design.oop.isp;

/**
 * Created by TangShengBo on 2017-10-22.
 */
public class Searcher extends AbstractSearcher {

    public Searcher(GreatTemperament greatTemperament) {
        super(greatTemperament);
    }

    public Searcher(GoodBodyPettyGirl goodBodyPettyGirl) {
        super(goodBodyPettyGirl);
    }

    @Override
    public void show() {
        System.out.println("----------------美女信息如下-----------");
        if (goodBodyPettyGirl != null) {
            goodBodyPettyGirl.goodLooking();
            goodBodyPettyGirl.niceFigure();
        }
        if (greatTemperament != null) {
            greatTemperament.greatTemperament();
        }
    }
}
