package com.tangshengbo.design.flyweight;

/**
 * Created by Tangshengbo on 2017/10/12.
 */
public class ConcreteWebSite implements WebSite {

    private String name;

    public ConcreteWebSite(String name) {
        this.name = name;
    }

    @Override
    public void use(User user) {
        System.out.println(String.format("网站分类：%s 用户：%s", name, user.getName()));
    }
}
