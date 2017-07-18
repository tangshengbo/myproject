package com.tangshengbo.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by TangShengBo on 2017-07-16.
 */
public class SimpleList<T> {

    private Object[] elementData;
    private int size = 16;

    public int size() {
        return elementData.length;
    }

    public SimpleList(){
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
        testArrayToList();


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
}
