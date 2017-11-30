package com.tangshengbo.collection.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

/**
 * Created by tangshengbo on 2017/3/23.
 */
public class MyComparable implements Comparable<MyComparable> {

    private int count;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyComparable(int count) {
        this.count = count;
    }

    public MyComparable() {
    }

    public MyComparable(int count, String name) {
        this.count = count;
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int compareTo(MyComparable o) {
        return this.getCount() - o.getCount();
    }

    @Override
    public String toString() {
        return "MyComparable{" +
                "count=" + count +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        List<MyComparable> comparables = new ArrayList<>();
        MyComparable comparable = new MyComparable(1, "z");
        MyComparable comparable2 = new MyComparable(2, "n");
        MyComparable comparable3 = new MyComparable(3, "l");
        MyComparable comparable4 = new MyComparable(4, "r");
        MyComparable comparable5 = new MyComparable(5, "a");
        comparables.add(comparable);
        comparables.add(comparable4);
        comparables.add(comparable3);
        comparables.add(comparable2);
        comparables.add(comparable5);

        System.out.println("No sort ========================================");
        for (MyComparable myComparable : comparables) {
            System.out.println(myComparable.getCount());
        }

        comparables.sort(Comparator.naturalOrder());
        System.out.println("Collections asc sort ========================================");
        for (MyComparable myComparable : comparables) {
            System.out.println(myComparable.getCount());
        }
        comparables.sort(Comparator.reverseOrder());
        System.out.println("Collections desc sort ========================================");
        comparables.forEach(myComparable -> System.out.println(myComparable.getCount()));
        System.out.println("Collections name sort ========================================");
        comparables.sort(comparing(MyComparable::getName));
        comparables.forEach(System.out::println);
        System.out.println("Collections count sort ========================================");
        comparables.sort((MyComparable m1, MyComparable m2) -> m1.getCount() - m2.getCount());
        comparables.forEach(System.out::println);
    }
}
