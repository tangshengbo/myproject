package com.tangshengbo.design.flyweight;

/**
 * Created by Tangshengbo on 2017/10/12.
 */
public class WebSiteTest {

    public static void main(String[] args) {
        WebSiteFactory factory = new WebSiteFactory();
        WebSite webSite;
        webSite = factory.getWebSite("天猫");
        webSite.use(new User("唐唐"));
        webSite = factory.getWebSite("京东");
        webSite.use(new User("雨夜画梦"));
        webSite = factory.getWebSite("京东");
        webSite.use(new User("小唐"));
        webSite = factory.getWebSite("当当");
        webSite.use(new User("唐波"));
        webSite = factory.getWebSite("当当");
        webSite.use(new User("唐"));

        System.out.println(String.format("网站分类总数:%s", factory.getWebSiteCount()));
    }
}
