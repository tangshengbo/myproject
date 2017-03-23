package com.tangshengbo.loadclass;

import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;

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
        int count = StringUtils.countMatches("lllslslllslllsllsllls", "s");
        System.out.println(count);
        LoadClassTest classTest = new LoadClassTest();
        Person person = new Person("lls", 123);
        Person newPerson = classTest.transmitObject();
        System.out.println(person.hashCode() + "\t" + newPerson.hashCode());
        System.out.println(person.toString());
        System.out.println("breakMultipleLoop================================================");
        classTest.breakMultipleLoop();
        System.out.println("reverseStr=======================================================");
        String result = classTest.reverseStr("qwertyuiop");
        System.out.println(result);
        System.out.println("replaceStr=======================================================");
        System.out.println(classTest.replaceStr( 0, result, "qwertyuiop"));
        System.out.println("reverseStrByCharArray============================================");
        result = classTest.reverseStrByCharArray("qwertyuiop");
        System.out.println(result);
        System.out.println("replaceEncode====================================================");
        result = classTest.replaceEncode("唐声波", "UTF-8");
        System.out.println(result);

    }

    public Person transmitObject() {
        Person person = new Person();
        person.setAge(10);
        person.setName("3322324234");
        return person;
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
}
