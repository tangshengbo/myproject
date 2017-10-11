package com.tangshengbo.design.proxy.print;

/**
 * Created by TangShengBo on 2017-10-11.
 */
public class PrinterProxy implements Printable {

    private String name;

    private Printer realPrinter;

    public PrinterProxy(String name) {
        this.name = name;
    }

    @Override
    public synchronized void setPrinterName(String name) {
        if (realPrinter != null) {
            realPrinter.setPrinterName(name);
        }
        this.name = name;
    }

    @Override
    public String getPrintName() {
        return name;
    }

    @Override
    public void print(String string) {
        this.realize();
        realPrinter.print(string);
    }

    private synchronized void realize() {
        if (realPrinter == null) {
            realPrinter = new Printer(name);
        }
    }

}
