package com.tangshengbo.design.command.example;

/**
 * Created by Tangshengbo on 2017/9/29.
 */
public class BakeMuttonCommand extends AbstractCommand implements Command {

    public BakeMuttonCommand(Barbecuer barbecuer, String name) {
        super(barbecuer, name);
    }

    @Override
    public void executeCommand() {
        barbecuer.bakeMutton();
    }

    @Override
    public String getName() {
        return name;
    }
}
