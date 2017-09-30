package com.tangshengbo.design.command.example;

/**
 * Created by Tangshengbo on 2017/9/29.
 */
public class BakeBeefCommand extends AbstractCommand implements Command {

    public BakeBeefCommand(Barbecuer barbecuer, String name) {
        super(barbecuer, name);
    }

    @Override
    public void executeCommand() {
        barbecuer.bakeBeef();
    }

    @Override
    public String getName() {
        return name;
    }
}
