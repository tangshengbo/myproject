package com.tangshengbo.design.command.example;

/**
 * Created by Tangshengbo on 2017/9/29.
 */
public class BakeBeefCommand extends Command {

    public BakeBeefCommand(Barbecuer barbecuer, String name) {
        super(barbecuer, name);
    }

    @Override
    protected void executeCommand() {
        barbecuer.bakeBeef();
    }
}
