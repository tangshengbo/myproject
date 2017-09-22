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
}

