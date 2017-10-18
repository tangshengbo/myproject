package com.tangshengbo.design.mediator;

/**
 * Created by Tangshengbo on 2017/10/17.
 */
public class UNSecurityCouncil implements UnitedNations {

    private USA usa;

    private Iraq iraq;

    @Override
    public void declare(String message, Country country) {
        if (country == usa) {
            iraq.getMessage(message);
            return;
        }
        if (country == iraq) {
            usa.getMessage(message);
            return;
        }
    }

    public USA getUsa() {
        return usa;
    }

    public void setUsa(USA usa) {
        this.usa = usa;
    }

    public Iraq getIraq() {
        return iraq;
    }

    public void setIraq(Iraq iraq) {
        this.iraq = iraq;
    }
}
