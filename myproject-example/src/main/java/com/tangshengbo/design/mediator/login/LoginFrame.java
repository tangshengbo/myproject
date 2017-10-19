package com.tangshengbo.design.mediator.login;

import org.apache.commons.lang.time.FastDateFormat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by TangShengBo on 2017-10-17.
 */
public class LoginFrame extends Frame implements ActionListener, Mediator {

    private ColleagueCheckBox checkGuest;
    private ColleagueCheckBox checkLogin;
    private ColleagueTextField textUser;
    private ColleagueTextField textPass;
    private ColleagueButton buttonOk;
    private ColleagueButton buttonCancel;

    public LoginFrame(String title) throws HeadlessException {
        super(title);
        setBackground(Color.lightGray);
        setLayout(new GridLayout(4, 2));

        createColleagues();

        add(checkGuest);
        add(checkLogin);
        add(new Label("username:"));
        add(textUser);
        add(new Label("password:"));
        add(textPass);
        add(buttonOk);
        add(buttonCancel);

        colleagueChanged();

        pack();
        show();
    }

    @Override
    public void createColleagues() {
        CheckboxGroup group = new CheckboxGroup();
        checkGuest = new ColleagueCheckBox("Guest", group, Boolean.TRUE);
        checkLogin = new ColleagueCheckBox("Login", group, Boolean.TRUE);

        textUser = new ColleagueTextField("", 10);
        textPass = new ColleagueTextField("", 10);
        textPass.setEchoChar('*');

        buttonOk = new ColleagueButton("OK");
        buttonCancel = new ColleagueButton("Cancel");

        checkGuest.setMediator(this);
        checkLogin.setMediator(this);
        textUser.setMediator(this);
        textPass.setMediator(this);
        buttonOk.setMediator(this);
        buttonCancel.setMediator(this);

        checkGuest.addItemListener(checkGuest);
        checkLogin.addItemListener(checkLogin);

        textUser.addTextListener(textUser);
        textPass.addTextListener(textPass);

        buttonOk.addActionListener(this);
        buttonCancel.addActionListener(this);
    }

    @Override
    public void colleagueChanged() {
        if (checkGuest.getState()) {
            textUser.setColleagueEnabled(Boolean.FALSE);
            textPass.setColleagueEnabled(Boolean.FALSE);
            buttonOk.setColleagueEnabled(Boolean.TRUE);
        } else {
            textUser.setColleagueEnabled(Boolean.TRUE);
            userPassChanged();
        }
    }

    private void userPassChanged() {
        if (textUser.getText().length() > 0) {
            textPass.setColleagueEnabled(Boolean.TRUE);
            if (textUser.getText().length() >= 4 && textPass.getText().length() >= 4) {
                buttonOk.setColleagueEnabled(Boolean.TRUE);
            } else {
                buttonOk.setColleagueEnabled(Boolean.FALSE);
            }
        } else {
            textPass.setText("");
            textPass.setColleagueEnabled(Boolean.FALSE);
            buttonOk.setColleagueEnabled(Boolean.FALSE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(e.getWhen()));
        System.out.println(e.toString());
        System.exit(0);
    }
}
