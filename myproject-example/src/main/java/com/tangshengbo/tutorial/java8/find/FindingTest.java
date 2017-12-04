package com.tangshengbo.tutorial.java8.find;

import com.tangshengbo.tutorial.java8.stream.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.tangshengbo.tutorial.java8.stream.Dish.menu;

/**
 * Created by Tangshengbo on 2017/12/4.
 */
public class FindingTest {

    public static void main(String... args) {
        if (isHealthAnyMatch()) {
            System.out.println("Vegetarian friendly");
        }
        System.out.println("===================================================");
        System.out.println(isHealthAllMatch());
        System.out.println("===================================================");
        System.out.println(isHealthNoneMatch());
        System.out.println("===================================================");
        findAny();
        System.out.println("===================================================");
        findFirst();
    }

    private static void findFirst() {
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                        .map(x -> x * x)
                        .filter(x -> x % 3 == 0)
                        .findFirst(); // 9
        firstSquareDivisibleByThree.ifPresent(System.out::println);
    }

    private static boolean isHealthAnyMatch() {
        return menu.stream().anyMatch(Dish::isVegetarian);
    }

    private static boolean isHealthAllMatch() {
        return menu.stream().allMatch(d -> d.getCalories() < 1000);
    }

    private static boolean isHealthNoneMatch() {
        return menu.stream().noneMatch(d -> d.getCalories() >= 1000);
    }

    private static void findAny() {
        Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }
}
