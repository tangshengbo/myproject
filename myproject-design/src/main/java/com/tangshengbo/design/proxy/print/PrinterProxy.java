package com.tangshengbo.design.proxy.print;

/**
 * Created by TangShengBo on 2017-10-11.
 */
public class PrinterProxy implements Printable {

    private String name;

    private Printer realPrinter;

    private String className;

    public PrinterProxy(String name, String className) {
        this.name = name;
        this.className = className;
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

    private void realize() {
        if (realPrinter == null) {
            synchronized (this) {
                try {
                    realPrinter = (Printer) Class.forName(className).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
