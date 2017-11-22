package com.tangshengbo.design.proxy.pursuer;

/**
 * Created by Tangshengbo on 2017/10/11.
 */
public class ProxyTest {

    public static void main(String[] args) {
        SchoolGirl girl = new SchoolGirl("小罗");
        PursuerProxy proxy = new PursuerProxy("小唐", girl);
        proxy.sendFlower();
        proxy.sendChocolate();
    }
}
