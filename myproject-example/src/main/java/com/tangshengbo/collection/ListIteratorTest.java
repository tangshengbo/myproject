package com.tangshengbo.collection;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Tangshengbo on 2017/12/7.
 */
public class ListIteratorTest {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 3, 6, 19, 20);
        ListIterator<Integer> listIterator = integers.listIterator();
        while (listIterator.hasNext()) {
            Integer integer = listIterator.next();
            System.out.println(integer);
        }
        System.out.println("=======================================");
        while (listIterator.hasPrevious()) {
            int i = listIterator.previous();
            System.out.println(i);
        }
    }
}
