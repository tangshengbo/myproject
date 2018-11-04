package com.tangshengbo.loadclass.asm;

/**
 * Created by Tangshengbo on 2018/10/23
 */
public class AccountImpl implements Account {

    static {
        System.out.println("class load............");
    }

    @Override
    public void operation() {
        System.out.println("operation...");
    }
}
