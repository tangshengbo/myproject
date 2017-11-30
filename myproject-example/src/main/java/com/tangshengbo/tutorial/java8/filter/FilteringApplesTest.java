package com.tangshengbo.tutorial.java8.filter;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Tangshengbo on 2017/11/28.
 */
public class FilteringApplesTest {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));
        lambdaModel(inventory);
        customModel(inventory);
        Runnable r = () -> System.out.println("Lambda example");
        new Thread(r, "Lambda").start();

        int portNumber = 1337;
        Runnable r2 = () -> System.out.println(portNumber);
        new Thread(r2, "Lambda").start();
    }

    private static void lambdaModel(List<Apple> inventory) {
        List<Apple> greenApples = filterApples(inventory, FilteringApplesTest::isGreenApple);
        System.out.println(greenApples);
//        greenApples = filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
//        System.out.println(greenApples);
//        greenApples = filterApples(inventory, (Apple a) -> "green".equals(a.getColor()) || a.getWeight() > 100);
//        greenApples = greenApples.stream().filter(apple -> apple.getWeight() > 100).collect(Collectors.toList());
        greenApples = greenApples.parallelStream().filter(apple -> apple.getWeight() > 1).collect(Collectors.toList());
        System.out.println(greenApples);
    }

    private static void customModel(List<Apple> inventory) {
        inventory = filter(inventory, new AppleColorPredicate());
        System.out.println(inventory);
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    private interface ApplePredicate {
        boolean test(Apple a);
    }

    private static class AppleWeightPredicate implements ApplePredicate {
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    private static class AppleColorPredicate implements ApplePredicate {
        public boolean test(Apple apple) {
            return "green".equals(apple.getColor());
        }
    }

    private static class AppleRedAndHeavyPredicate implements ApplePredicate {
        public boolean test(Apple apple) {
            return "red".equals(apple.getColor())
                    && apple.getWeight() > 150;
        }
    }
}
