package com.tangshengbo.io.netty;

import java.io.Serializable;

/**
 * Created by Tang on 2017/7/17.
 */
public class Command implements Serializable {

    private static final long serialVersionUID = 4785783750460318935L;

    private String actionName;

    public Command(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    @Override
    public String toString() {
        return "Command{" +
                "actionName='" + actionName + '\'' +
                '}';
    }
}
