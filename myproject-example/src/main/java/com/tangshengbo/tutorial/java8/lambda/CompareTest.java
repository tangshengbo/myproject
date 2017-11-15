package com.tangshengbo.tutorial.java8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Tangshengbo on 2017/11/3.
 */
public class CompareTest {

    private static List<String> names = Arrays.asList("1", "2", "5", "3");

    private static void oldCompare() {
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
    }

    private static void newCompare() {
        Collections.sort(names, (String a, String b) -> b.compareTo(a));
    }

    public static void main(String[] args) {
//        oldCompare();
        newCompare();
        names.forEach(System.out::println);
    }

}