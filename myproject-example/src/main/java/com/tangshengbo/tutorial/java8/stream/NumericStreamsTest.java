package com.tangshengbo.tutorial.java8.stream;

import java.util.Comparator;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.tangshengbo.tutorial.java8.stream.Dish.menu;

/**
 * Created by Tangshengbo on 2017/12/5.
 */
public class NumericStreamsTest {

    public static void main(String... args) {
        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println("Number of calories:" + calories);
        Stream<Integer> integerStream = menu.stream()
                .mapToInt(Dish::getCalories)
                .boxed().sorted(Comparator.reverseOrder());
        integerStream.forEach(System.out::println);
        System.out.println("===================================================");

        // max and OptionalInt
        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        System.out.println(maxCalories.orElse(0));
        System.out.println("===================================================");
        // numeric ranges
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
               .filter(n -> n % 2 == 0);
        evenNumbers.forEach(n -> System.out.print(n +"-"));
        System.out.println();
        System.out.println("===================================================");
        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a -> IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).boxed()
                                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));

        pythagoreanTriples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }
}
