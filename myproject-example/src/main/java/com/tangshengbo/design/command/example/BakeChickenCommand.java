package com.tangshengbo.design.command.example;

/**
 * Created by Tangshengbo on 2017/9/29.
 */
public class BakeChickenCommand extends Command {

    public BakeChickenCommand(Barbecuer barbecuer, String name) {
        super(barbecuer, name);
    }

    @Override
    protected void executeCommand() {
        barbecuer.bakeChickenWing();
    }
}
