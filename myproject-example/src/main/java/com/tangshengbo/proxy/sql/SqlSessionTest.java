package com.tangshengbo.proxy.sql;

/**
 * Created by Tangshengbo on 2017/10/11.
 */
public class SqlSessionTest {

    public static void main(String[] args) {
        SqlSessionProxy proxy = new SqlSessionProxy();
        SqlSession sqlSessionProxy = (SqlSession) proxy.getInstance(new SqlSessionImpl());
        sqlSessionProxy.select();
    }
}
