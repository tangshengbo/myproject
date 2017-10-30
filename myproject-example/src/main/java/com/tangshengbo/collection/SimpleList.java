package com.tangshengbo.collection;

import java.util.*;

/**
 * Created by TangShengBo on 2017-07-16.
 */
public class SimpleList<T> {

    private Object[] elementData;
    private int size = 16;

    public int size() {
        return elementData.length;
    }

    public SimpleList() {
        elementData = new Object[size];
    }

    public boolean isEmpty() {
        return elementData.length == 0;
    }

    public boolean add(T e) {
        int currentIdx = elementData.length;
        if (elementData.length == size) {
            elementData = Arrays.copyOf(elementData, size * 2);
            elementData[currentIdx + 1] = e;
        }
        return false;
    }

    public boolean remove(Object o) {
        return false;
    }

    public T get(int index) {
        return null;
    }

    public static void main(String[] args) {
//        SimpleList<Integer> list = new SimpleList<>();
//        for (int i = 0; i < 100; i++) {
//            list.add(i);
//        }
//        System.out.println(list.size);
//
//        ArrayList arrayList = new ArrayList();
//        for (int i = 0; i < 100; i++) {
//            arrayList.add(i);
//        }
//        System.out.println(10 >> 1);
        testList();
//        testArrayToList();
//
//
//        String s1 = "hello";
//        String s2 = "world";
//        change(s1, s2);
//        StringBuilder sb1 = new StringBuilder("hello");
//        StringBuilder sb2 = new StringBuilder("hello");
//        change(sb1, sb2);
//        System.out.println(s1 + "\t" + s2);
//        System.out.println(sb1 + "\t" + sb2);
//
//        testCollections();

    }

    private static void change(String s1, String s2) {
        s1 = s2;
        s2 = s1 + s2;
    }

    private static void change(StringBuilder s1, StringBuilder s2) {
        s1 = s2;
        s2.append("world");
    }

    private static void testArrayToList() {
        String[] arr = new String[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = String.valueOf(i);
        }

        List<String> list = Arrays.asList(arr);

        //list.add("1");
        for (String str : list) {
            System.out.println(str);
        }

        List<String> stringList = new ArrayList<>(list);
        stringList.add("10");
        for (String str : stringList) {
            System.out.println(str);
        }
    }

    private static void testCollections() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        Collections.shuffle(numbers);
        System.out.println(numbers);
        System.out.println("----------------------------------");
        Collections.reverse(numbers);
        System.out.println(numbers);
        System.out.println("----------------------------------");
        Collections.sort(numbers);
        System.out.println(numbers);
        System.out.println("----------------------------------");
        Collections.rotate(numbers, 3);
        System.out.println(numbers);
    }

    private static void testList() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(4);
        numbers.add(5);
        numbers.add(5);

        Set set = new HashSet<>(numbers);
        numbers.clear();
        numbers.addAll(set);
        numbers.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 01 - 02;
            }
        });

        numbers.forEach(integer -> {
            System.out.println(integer);
        });
    }
}
