package com.tangshengbo.design.oop.isp;

/**
 * Created by TangShengBo on 2017-10-22.
 */
public class PettyGirlTest {

    public static void main(String[] args) {
        GoodBodyPettyGirl goodBodyPettyGirl = new PettyGirlImpl("兰兰");
        AbstractSearcher searcher = new Searcher(goodBodyPettyGirl);
        searcher.show();

        GreatTemperament greatTemperament = new PettyGirlImpl("兰兰");
        searcher = new Searcher(greatTemperament);
        searcher.show();
    }
}
