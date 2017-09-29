package com.tangshengbo.design.command.example;

/**
 * Created by Tangshengbo on 2017/9/29.
 */
public abstract class Command {

    protected String name;

    protected Barbecuer barbecuer;

    public Command(Barbecuer barbecuer, String name) {
        this.barbecuer = barbecuer;
        this.name = name;
    }

    protected abstract void executeCommand();
}
