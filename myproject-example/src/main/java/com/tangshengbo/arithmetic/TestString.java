package com.tangshengbo.arithmetic;

import com.google.common.collect.Lists;
import jodd.util.StringUtil;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tangshengbo on 2017/10/19.
 */
public class TestString {

    private static final int size = 50000;

    public static void main(String[] args) {
//        testPlusStr();
//        spiltStr();
        replaceStr();
    }

    private static void testPlusStr() {
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

    private void breakMultipleLoop() {
        //跳出多重循环
        label:
        for (int i = 0; i < 100; i++) {
            System.out.println("i:" + i);
            for (int j = 0; j < 100; j++) {
                System.out.println("j:" + j);
                break label;
            }
        }
        System.out.println("label:break");
    }

    public String reverseStr(String targetStr) {
        StringBuffer sb = new StringBuffer(targetStr);
        sb.reverse();
        return sb.toString();
    }

    public String reverseStrByCharArray(String targetStr) {
        if (targetStr == null) {
            return "";
        }
        char[] chars = targetStr.toCharArray();
        String reverse = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            reverse += chars[i];
        }
        System.out.println(reverse.valueOf(chars, 0, 5));
        return reverse;
    }

    public String replaceStr(int index, String srcStr, String targetStr) {
        StringBuffer sb = new StringBuffer(srcStr);
        sb.replace(index, srcStr.length(), targetStr);
        return sb.toString();
    }

    public String replaceEncode(String targetStr, String encodeType) {
        if (targetStr == null) {
            return "";
        }
        if (encodeType == null) {
            return "";
        }
        String newStr;
        try {
            newStr = new String(targetStr.getBytes(), encodeType);
        } catch (UnsupportedEncodingException e) {
            return "不支持编码格式";
        }
        return newStr;
    }

    private static void joinStr() {
        List<String> names = Lists.newArrayList();
        names.add("tang");
        names.add("sheng");
        names.add("bo");
        System.out.println(String.join("-", names));
    }

    private static void spiltStr() {
        String str = "20170906,3000000182,1001215854,HB_TZR_3261041,433.00,CNY,,,,,,,";
        System.out.println(Arrays.toString(str.split(",", -1)));
        System.out.println(Arrays.toString(StringUtils.split(str, ",")));
        System.out.println(Arrays.toString(StringUtil.split(str, ",")));
        System.out.println(StringUtil.removeChars(str, ',', '.'));
    }

    private static void replaceStr() {
        String str = "20170906,3000000182,1001215854,HB_TZR_3261041,433.00,CNY,,,,,,,";
        System.out.println(str.replace("20170906", "201701113"));
        System.out.println(StringUtils.join(str.split(",", -1), "-"));
        System.out.println(StringUtils.replace(str, "20170906", "201701113"));
    }
}


