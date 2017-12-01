package com.tangshengbo.tutorial.java8.stream;

import java.util.*;
import java.util.stream.Collectors;

import static com.tangshengbo.tutorial.java8.stream.Dish.menu;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by Tangshengbo on 2017/12/1.
 */
public class StreamTest {

    public static void main(String... args) {
        // Java 7
        getLowCaloricDishesNamesInJava7(menu).forEach(System.out::println);
        System.out.println("-----------------------------------------------");
        // Java 8
        getLowCaloricDishesNamesInJava8(menu).forEach(System.out::println);
        //group
        Map<Dish.Type, List<Dish>> dishesByType =
                menu.stream().collect(groupingBy(Dish::getType));
        dishesByType.forEach((k, v) -> System.out.println("Key : " + k + " \t Value : " + v));
    }

    private static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish d : dishes) {
            if (d.getCalories() < 500) {
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for (Dish d : lowCaloricDishes) {
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }

    private static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
        return dishes.parallelStream()
                .filter(d -> d.getCalories() < 500)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }
}
