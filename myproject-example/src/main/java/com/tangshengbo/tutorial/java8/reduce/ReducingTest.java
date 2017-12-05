package com.tangshengbo.tutorial.java8.reduce;

import com.tangshengbo.tutorial.java8.stream.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.tangshengbo.tutorial.java8.stream.Dish.menu;

/**
 * Created by Tangshengbo on 2017/12/4.
 */
public class ReducingTest {

    public static void main(String...args){
        List<Integer> numbers = Arrays.asList(9,4,5,1,2);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);
        System.out.println("===================================================");
        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);
        System.out.println("===================================================");
        int max = numbers.stream().reduce(0, Integer::max);
        System.out.println(max);
        System.out.println("===================================================");
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);
        System.out.println("===================================================");
        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println("Number of calories:" + calories);
    }
}
