package com.tangshengbo.optimization.multiextend;

/**
 * Created by Tangshengbo on 2017/10/16.
 */
public class MultiExtendTest {

    public static void main(String[] args) {
        Son son = new Son();
        System.out.println(String.format("strong:%s kind:%s", son.strong(), son.kind()));
        Daughter daughter = new Daughter();
        System.out.println(String.format("strong:%s kind:%s", daughter.strong(), daughter.kind()));
    }
}
