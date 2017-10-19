package com.tangshengbo.arithmetic;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tangshengbo on 2017/10/19.
 */
public class TestString {

    private static final int size = 50000;

    public static void main(String[] args) {
        TestString testString = new TestString();
        testString.testPlus();
        testString.testConcat();
        testString.testJoin();
        testString.testStringBuffer();
        testString.testStringBuilder();
    }

    public void testPlus() {
        String s = "";
        long ts = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            s = s + String.valueOf(i);
        }
        long te = System.currentTimeMillis();
        System.out.println(String.format("+ cost %s ms", te - ts));
    }

    public void testConcat() {
        String s = "";
        long ts = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            s = s.concat(String.valueOf(i));
        }
        long te = System.currentTimeMillis();
        System.out.println(String.format("concat cost %s ms", te - ts));
    }

    public void testJoin() {
        List<String> list = new ArrayList<String>();
        long ts = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            list.add(String.valueOf(i));
        }
        StringUtils.join(list, "");
        long te = System.currentTimeMillis();
        System.out.println(String.format("StringUtils.join cost %s ms", te - ts));
    }

    public void testStringBuffer() {
        StringBuffer sb = new StringBuffer();
        long ts = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            sb.append(String.valueOf(i));
        }
        sb.toString();
        long te = System.currentTimeMillis();
        System.out.println(String.format("StringBuffer cost %s ms", te - ts));
    }

    public void testStringBuilder() {
        StringBuilder sb = new StringBuilder();
        long ts = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            sb.append(String.valueOf(i));
        }
        sb.toString();
        long te = System.currentTimeMillis();
        System.out.println(String.format("StringBuilder cost %s ms", te - ts));
    }
}
