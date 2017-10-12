package com.tangshengbo.design.proxy.print;

/**
 * Created by TangShengBo on 2017-10-11.
 */
public class PrinterTest {

    public static void main(String[] args) {
        Printable printable = new PrinterProxy("唐唐", "com.tangshengbo.design.proxy.print.Printer");
        System.out.println(String.format("现在的名字是%s", printable.getPrintName()));
        System.out.println(String.format("现在的名字是%s", printable.getPrintName()));
        printable.print("hello world");
        printable.setPrinterName("小唐");
        printable.print("hello world");
    }
}
