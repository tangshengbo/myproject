package com.tangshengbo.design.proxy.print;

import java.util.concurrent.TimeUnit;

/**
 * Created by TangShengBo on 2017-10-11.
 */
public class Printer implements Printable {

    private String name;

    public Printer(String name) {
        this.name = name;
        heavyJob(name);
    }

    public Printer() {
        heavyJob(name);
    }

    @Override
    public void setPrinterName(String name) {
        this.name = name;
    }

    @Override
    public String getPrintName() {
        return name;
    }

    @Override
    public void print(String string) {
        System.out.println("====" + name + "============");
        System.out.println(string);
    }

    private void heavyJob(String msg) {
        System.out.println(msg);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束");
    }
}
