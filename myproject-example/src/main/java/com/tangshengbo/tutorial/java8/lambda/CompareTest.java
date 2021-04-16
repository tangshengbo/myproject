package com.tangshengbo.tutorial.java8.lambda;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Tangshengbo on 2017/11/3.
 */
public class CompareTest {

    private static List<String> names = Arrays.asList("1", "2", null, "3");

    private static void oldCompare() {
        Collections.sort(names, (a, b) -> b.compareTo(a));
    }

    private static void newCompare() {
//        Collections.sort(names, (String a, String b) -> b.compareTo(a));
        names.sort(String::compareTo);
    }

    public static void main(String[] args) {
//        oldCompare();
//        newCompare();

//        List<String> collect = names.stream().sorted().reversed()).collect(Collectors.toList());
//        System.out.println(collect);
        List<User> list = Lists.newArrayList(new User("xiao_ming", 21), new User("xiao_hua", 22), new User(null, 23), new User("xxxxxxx", 23));
        List<User> nList = list.stream().sorted(
                Comparator.comparing(User::getName, Comparator.nullsLast(String::compareTo)))
                .collect(Collectors.toList());

        for (User user : nList) {

            System.out.println(JSON.toJSON(user));
        }



    }

    private static void example() {
        Function<String, Integer> stringToInteger = Integer::parseInt;
        System.out.println(stringToInteger);
    }
    private static class User {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }



}
