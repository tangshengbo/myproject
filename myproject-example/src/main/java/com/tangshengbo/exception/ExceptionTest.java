package com.tangshengbo.exception;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tangshengbo on 2017/2/14.
 */
public class ExceptionTest {

    public static void main(String[] args) {
        ExceptionTest test = new ExceptionTest();
        String result = test.testFinally();
        System.out.println(result);
        BigDecimal bigDecimal = BigDecimal.valueOf(0.0);
        if (bigDecimal.doubleValue() <= 0) {
            System.out.println(bigDecimal.intValue());
        }
        String str = String.format("Hi,%s", "王力");
        List<String> names = Lists.newArrayList();
        names.add("tang");
        names.add("sheng");
        names.add("bo");
        System.out.println(String.join("-", names));
        System.out.println(str);
        test.calc();


    }

    public String testFinally() {
        try {
            return "try";
        } catch (Exception e) {

        } finally {
            System.out.println("32423423");
//            return "finally";
        }
        return "finally";
    }

    public void calc() {
        BigDecimal bigDecimal1 = new BigDecimal(11.22);
        BigDecimal bigDecimal2 = new BigDecimal(12.2200000000000032232332);
        BigDecimal bigDecimal3 = new BigDecimal(23.440000000000000000000000002344);
        BigDecimal bigDecimal4 = new BigDecimal(bigDecimal1.add(bigDecimal2).toString());

        System.out.println(bigDecimal3.compareTo(bigDecimal4));
        bigDecimal1.getClass();



    }
}

