package com.tangshengbo.tutorial.java8.filter;

import com.tangshengbo.tutorial.java8.stream.Dish;

import java.util.Arrays;
import java.util.List;

import static com.tangshengbo.tutorial.java8.stream.Dish.menu;
import static java.util.stream.Collectors.toList;

/**
 * Created by Tangshengbo on 2017/12/4.
 */
public class FilteringTest {

    public static void main(String...args){

        // Filtering with predicate
        List<Dish> vegetarianMenu =
                menu.stream()
                        .filter(Dish::isVegetarian)
                        .collect(toList());

        vegetarianMenu.forEach(System.out::println);

        // Filtering unique elements
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
        System.out.println("===================================================");

        // Truncating a stream
        List<Dish> dishesLimit3 =
                menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .limit(3)
                        .collect(toList());

        dishesLimit3.forEach(System.out::println);
        System.out.println("===================================================");
        // Skipping elements
        List<Dish> dishesSkip2 =
                menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .skip(2)
                        .collect(toList());
        dishesSkip2.forEach(System.out::println);

        System.out.println("===================================================");
        if(menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }
    }
}
