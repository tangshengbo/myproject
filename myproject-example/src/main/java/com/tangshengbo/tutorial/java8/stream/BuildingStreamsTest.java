package com.tangshengbo.tutorial.java8.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Tangshengbo on 2017/12/5.
 */
public class BuildingStreamsTest {

    public static void main(String... args) throws Exception {
        of();
        System.out.println("===================================================");
        empty();
        System.out.println("===================================================");
        arrays();
        System.out.println("===================================================");
        iterate();
        System.out.println("===================================================");
        generateByRandom();
        System.out.println("===================================================");
        generate();
        System.out.println("===================================================");
        readFile();
    }

    private static void of() {
        // Stream.of
        Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
    }

    private static void arrays() {
        // Arrays.stream
        int[] numbers = {2, 3, 5, 7, 11, 13};
        System.out.println(Arrays.stream(numbers).max().orElse(0));
    }

    private static void empty() {
        // Stream.empty
        Stream<String> emptyStream = Stream.empty();
        System.out.println(emptyStream);
    }

    private static void iterate() {
        // Stream.iterate
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
        System.out.println("===================================================");
        // fibonnaci with iterate
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));
        System.out.println("===================================================");
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);
    }

    private static void generateByRandom() {
        // generateByRandom stream of doubles with Stream.generate
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);
    }

    private static void generate() {
        // stream of 1s with Stream.generate
        IntStream.generate(() -> 1)
                .limit(5)
                .forEach(System.out::println);
        System.out.println("===================================================");
        IntStream.generate(() -> 2).limit(5).forEach(System.out::println);
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            public int getAsInt() {
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return this.previous;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }

    private static void readFile() throws IOException {
        long uniqueWords = Files.lines(Paths.get("E:/20171114_3000000255_ALLBALANCE.txt"), Charset.defaultCharset())
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .distinct()
                .count();
        System.out.println("There are " + uniqueWords + " unique words in data.txt");
    }
}
