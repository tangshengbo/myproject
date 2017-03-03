package com.tangshengbo.loadclass;

import org.apache.commons.lang.StringUtils;

/**
 * Created by tangshengbo on 2017/3/1.
 */
public class LoadClassTest {

    public static void main(String[] args) throws ClassNotFoundException {

        //Class clazz = Class.forName("");

        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        //LoadClassTest.class.getClass();
        Thread.currentThread().isInterrupted();

        //Class clazz = getClass().getClassLoader().loadClass("");
        int count = StringUtils.countMatches("lllslslllslllsllsllls","s");
        System.out.println(count);



    }
}
