package com.tangshengbo.design.chain;

/**
 * Created by tangshengbo on 2017/3/5.
 */
public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Console:" + message);
    }
}
