package com.tangshengbo.design.template.reflect;

/**
 * Created by Tangshengbo on 2017/12/22.
 */
public class DefaultLoader extends AbstractLoader {

    public void initUserName() {
        System.out.println("加载用户名......................");
    }

    public void initPassword() {
        System.out.println("加载密码........................");
    }

    public int initDB() {
        System.out.println("加载数据库.....................");
        return 0;
    }
}
