package com.tangshengbo.arithmetic;

import org.apache.commons.codec.binary.Base64;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by TangShengBo on 2017-05-19.
 */
public class TestBase64 {

    public static void main(String[] args) {
        testBase64Apache();
        testBase64Java8();
    }

    private static void testBase64Apache() {
        String str = "Hello World Apache";
        try {
            byte[] encodeBase64 = Base64.encodeBase64(str.getBytes("UTF-8"));
            System.out.println("Apache RESULT: En\t" + new String(encodeBase64));
            encodeBase64 = Base64.decodeBase64(encodeBase64);
            System.out.println("Apache RESULT: De\t" + new String(encodeBase64));
            System.out.println("=====================================================================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testCollectionSort(ConcurrentHashMap concurrentHashMap) {
        concurrentHashMap.get("");
        String str2 = "a,b,c,,";
        String[] ary = str2.split(",");
        //预期大于 3，结果是 3
        System.out.println(ary.length);
        //定义一个数组
        Integer[] arr = {1, 3, 66, 4, 78, 55, 9, 4, 3, 99};
        //将数组转成集合
        List<Integer> list1 = Arrays.asList(arr);
        Set<Integer> set = new HashSet<Integer>(list1);
        List<Integer> list2 = new ArrayList<Integer>();
        list2.addAll(set);
        System.err.println("去重:" + list2);
        Collections.sort(list2);
        System.out.println("排序:" + list2);
        Integer[] newArr = new Integer[list2.size()];
        newArr = list2.toArray(newArr);
        for (Integer integer : newArr) {
            System.out.println(integer);
        }
        concurrentHashMap.entrySet();
    }

    public void today() {
        if (isBusy()) {
            return;
        }

        if (isFree()) {
            return;
        }

        System.out.println("isHappy");
    }

    private boolean isFree() {
        return true;
    }

    private boolean isBusy() {
        return false;
    }

    private static int testFinally() {
        try {
            return 1;
        } finally {
            System.out.println("32353453443534534534534");
//            return 2;
        }
    }

    private static void testBase64Java8() {
        String str = "Hello World java8";
        byte[] encodeBase64;
        byte[] decodeBase64;
        String encodeStr;
        String decodeStr;
        try {
            encodeBase64 = java.util.Base64.getEncoder().encode(str.getBytes("UTF-8"));
            encodeStr = new String(encodeBase64);
            System.out.println("Java8 RESULT: En\t" + encodeStr);
            decodeBase64 = java.util.Base64.getDecoder().decode(encodeStr);
            decodeStr = new String(decodeBase64);
            System.out.println("Java8 RESULT: De\t" + decodeStr);

            System.out.println("=====================================================================");
            String url = "http://www.runoob.com/java/java8-base64.html";
            encodeBase64 = java.util.Base64.getUrlEncoder().encode(url.getBytes());
            encodeStr = new String(encodeBase64);
            System.out.println("Java8 Url RESULT: En\t" + encodeStr);
            decodeBase64 = java.util.Base64.getUrlDecoder().decode(encodeStr);
            decodeStr = new String(decodeBase64);
            System.out.println("Java8 Url RESULT: De\t" + decodeStr);

            System.out.println("=====================================================================");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 5; ++i) {
                stringBuilder.append(UUID.randomUUID().toString());
            }
            String mime = stringBuilder.toString();
            encodeBase64 = java.util.Base64.getMimeEncoder().encode(mime.getBytes());
            encodeStr = new String(encodeBase64);
            System.out.println("Java8 Mime RESULT: En\t" + encodeStr);
            decodeBase64 = java.util.Base64.getMimeDecoder().decode(encodeStr);
            decodeStr = new String(decodeBase64);
            System.out.println("Java8 Mime RESULT: De\t" + decodeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
