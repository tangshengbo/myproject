package com.tangshengbo.innerclass;

/**
 * Created by Tang on 2017/7/4.
 */
public class Code extends CodeFather {

    static {
        System.out.println("Code的静态代码块");
    }

    public Code() {
        System.out.println("Code的构造方法");
    }

    {
        System.out.println("Code的构造块");
    }

    public static void main(String[] args) {
        System.out.println("main");
        new Code();
    }
}
