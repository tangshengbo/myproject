package com.tangshengbo.collection;

import com.tangshengbo.tutorial.java8.stream.Dish;

import java.util.Collection;
import java.util.LinkedList;

import static com.tangshengbo.tutorial.java8.stream.Dish.menu;
import static java.util.stream.Collectors.toCollection;

/**
 * Created by Tangshengbo on 2017/12/8.
 */
public class LinkedListTest {

    public static void main(String[] args) {
        System.out.println(100 << 1);
        System.out.println(100 >> 1);
        LinkedList<Integer> linkedList = new LinkedList<>();
        int size = 100000;
        for (int i = 0; i < size; i++) {
            linkedList.add(i);
        }
        System.out.println(linkedList);
        System.out.println("===================================================");
        Collection<Dish> dishes = menu.stream().collect(toCollection(LinkedList::new));
        System.out.println(dishes);
        System.out.println("===================================================");
        forByIndex(linkedList, size);
//        forIterator(linkedList);
    }

    private static void forIterator(LinkedList<Integer> linkedList) {
        long ts = System.currentTimeMillis();
        linkedList.forEach(System.out::println);
        long te = System.currentTimeMillis();
        System.out.println(String.format("+ forIterator %s ms", te - ts));
    }

    private static void forByIndex(LinkedList<Integer> linkedList, int size) {
        long ts = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            System.out.println(linkedList.get(i));
        }
        long te = System.currentTimeMillis();
        System.out.println(String.format("+ forByIndex %s ms", te - ts));
    }
}
