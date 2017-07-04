package com.tangshengbo.innerclass;

/**
 * Created by Tang on 2017/7/4.
 */
public class CodeFather {

    static {
        System.out.println("CodeFather的静态代码块");
    }

    public CodeFather() {
        System.out.println("CodeFather的构造方法");
    }

    {
        System.out.println("CodeFather的构造块");
    }
}
