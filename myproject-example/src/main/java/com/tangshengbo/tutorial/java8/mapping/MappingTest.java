package com.tangshengbo.tutorial.java8.mapping;

import com.tangshengbo.tutorial.java8.stream.Dish;

import java.util.Arrays;
import java.util.List;

import static com.tangshengbo.tutorial.java8.stream.Dish.menu;
import static java.util.stream.Collectors.toList;

/**
 * Created by Tangshengbo on 2017/12/4.
 */
public class MappingTest {

    public static void main(String... args) {
        // map
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(toList());
        System.out.println(dishNames);
        System.out.println("===================================================");

        // map
        List<Integer> wordLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);
        System.out.println("===================================================");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares =
                numbers.stream()
                        .map(n -> n * n)
                        .collect(toList());
        System.out.println(squares);
        System.out.println("===================================================");

        List<String> words = Arrays.asList("Hello", "World");
        // flatMap
        words.stream()
                .flatMap((String line) -> Arrays.stream(line.split("")))
                .distinct()
                .forEach(System.out::println);
        System.out.println("===================================================");

        // flatMap
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> numbers2 = Arrays.asList(6, 7, 8);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap((Integer i) -> numbers2.stream()
                                .map((Integer j) -> new int[]{i, j})
                        )
                        .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                        .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
    }
}
