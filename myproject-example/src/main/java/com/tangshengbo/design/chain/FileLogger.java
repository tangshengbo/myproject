package com.tangshengbo.design.chain;

/**
 * Created by tangshengbo on 2017/3/5.
 */
public class FileLogger extends AbstractLogger {

    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File:" + message);
    }
}
