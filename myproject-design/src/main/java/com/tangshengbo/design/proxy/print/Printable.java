package com.tangshengbo.design.proxy.print;

/**
 * Created by TangShengBo on 2017-10-11.
 */
public interface Printable {

    void setPrinterName(String name);

    String getPrintName();

    void print(String string);
}
