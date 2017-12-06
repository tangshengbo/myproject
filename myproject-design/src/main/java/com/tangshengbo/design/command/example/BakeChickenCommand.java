package com.tangshengbo.design.command.example;

/**
 * Created by Tangshengbo on 2017/9/29.
 */
public class BakeChickenCommand extends AbstractCommand implements Command {

    public BakeChickenCommand(Barbecuer barbecuer, String name) {
        super(barbecuer, name);
    }

    @Override
    public void executeCommand() {
        barbecuer.bakeChickenWing();
    }

    @Override
    public String getName() {
        return name;
    }
}
