package com.tangshengbo.design.mediator.login;

import java.awt.*;

/**
 * Created by TangShengBo on 2017-10-17.
 */
public class ColleagueButton extends Button implements Colleague {

    private Mediator mediator;

    public ColleagueButton(String label) throws HeadlessException {
        super(label);
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void setColleagueEnabled(boolean enabled) {
        setEnabled(enabled);
    }
}
