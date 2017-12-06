package com.tangshengbo.design.mediator;

/**
 * Created by Tangshengbo on 2017/10/17.
 */
public class MediatorTest {

    public static void main(String[] args) {
        UNSecurityCouncil unitedNations = new UNSecurityCouncil();
        USA usa = new USA(unitedNations);
        Iraq iraq = new Iraq(unitedNations);

        unitedNations.setIraq(iraq);
        unitedNations.setUsa(usa);

        usa.declare("不准研制核武器");
        iraq.declare("我们没有核武器，也不怕侵略");
    }
}
