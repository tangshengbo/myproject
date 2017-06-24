package com.tangshengbo.arithmetic;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by TangShengBo on 2017-05-19.
 */
public class TestBase64 {

    public static void main(String[] args) {
        String str = "Hello World";
        try {
            byte[] encodeBase64 = Base64.encodeBase64(str.getBytes("UTF-8"));
            System.out.println("RESULT: En\t" + new String(encodeBase64));
            encodeBase64 = Base64.decodeBase64(encodeBase64);
            System.out.println("RESULT: De\t" + new String(encodeBase64));
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(16);
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
            System.out.println(testFinally());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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

    public static int testFinally() {
        try {
            return 1;
        }finally {
            System.out.println("32353453443534534534534");
//            return 2;
        }


    }
}
