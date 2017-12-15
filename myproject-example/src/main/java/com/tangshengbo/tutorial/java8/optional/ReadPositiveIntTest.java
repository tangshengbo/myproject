package com.tangshengbo.tutorial.java8.optional;

import java.util.Optional;
import java.util.Properties;

import static java.util.Optional.ofNullable;

/**
 * Created by Tangshengbo on 2017/12/15.
 */
public class ReadPositiveIntTest {

    public static void main(String[] args) {
        Optional<Integer> integerOptional = s2i("1");
        integerOptional.ifPresent(System.out::print);
        Properties props = new Properties();
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");
        System.out.println(readDurationImperative(props, "a"));
        System.out.println(readDurationWithOptional(props, "a"));
    }

    private static int readDurationImperative(Properties props, String name) {
        String value = props.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException ignored) {
            }
        }
        return 0;
    }

    private static Optional<Integer> s2i(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static int readDurationWithOptional(Properties props, String name) {
        return ofNullable(props.getProperty(name))
                .flatMap(ReadPositiveIntTest::s2i)
                .filter(i -> i > 0).orElse(0);
    }
}
