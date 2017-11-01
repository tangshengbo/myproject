package com.tangshengbo.proxy.sql;

/**
 * Created by Tangshengbo on 2017/10/11.
 */
public class SqlSessionImpl implements SqlSession {

    @Override
    public void select() {
        System.out.println("SqlSessionImpl select * from table...................");
        int i = 100 / 0;
    }
}
