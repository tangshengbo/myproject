package com.tangshengbo.collection;

import java.util.ArrayList;
import java.util.Arrays;

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
        SimpleList<Integer> list = new SimpleList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        System.out.println(list.size);

        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 100; i++) {
            arrayList.add(i);
        }
        System.out.println(10 >> 1);

    }
}
