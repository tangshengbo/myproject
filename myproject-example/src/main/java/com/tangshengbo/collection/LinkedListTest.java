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
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            linkedList.add(i);
        }
        System.out.println(linkedList);

        Collection<Dish> dishes = menu.stream().collect(toCollection(LinkedList::new));
        System.out.println(dishes);
    }
}
