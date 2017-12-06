package com.tangshengbo.design.command.example;

/**
 * Created by Tangshengbo on 2017/9/30.
 */
public abstract class AbstractCommand implements Command {

    protected String name;

    protected Barbecuer barbecuer;

    public AbstractCommand(Barbecuer barbecuer, String name) {
        this.barbecuer = barbecuer;
        this.name = name;
    }

}
