package com.tangshengbo.design.mediator.login;

import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

/**
 * Created by TangShengBo on 2017-10-17.
 */
public class ColleagueTextField extends TextField implements TextListener, Colleague {

    private Mediator mediator;

    public ColleagueTextField(String text, int columns) throws HeadlessException {
        super(text, columns);
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void setColleagueEnabled(boolean enabled) {
        setEnabled(enabled);
        setBackground(enabled ? Color.white : Color.lightGray);
    }
    @Override
    public void textValueChanged(TextEvent e) {
        this.mediator.colleagueChanged();
    }
}
