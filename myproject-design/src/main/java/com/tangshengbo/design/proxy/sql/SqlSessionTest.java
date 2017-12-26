package com.tangshengbo.design.proxy.sql;

/**
 * Created by Tangshengbo on 2017/10/11.
 */
public class SqlSessionTest {

    public static void main(String[] args) {
        SqlSessionProxy proxy = new SqlSessionProxy(new SqlSessionImpl());
        SqlSession sqlSessionProxy = proxy.getProxy();
        sqlSessionProxy.select();
    }
}
