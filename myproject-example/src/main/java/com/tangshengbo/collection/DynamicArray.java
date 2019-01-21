package com.tangshengbo.collection;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by TangShengBo on 2017-08-22.
 */
public class DynamicArray<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public DynamicArray() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity >= minCapacity) {
            return;
        }
        int newCapacity = oldCapacity * 2;
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
//        elementData = Arrays.copyOf(elementData, newCapacity);
        Object[] newElementData = new Object[newCapacity];
        System.arraycopy(elementData, 0,newElementData , 0, elementData.length);
        elementData = newElementData;
    }

    public void add(E e) {
        ensureCapacity(size + 1);
        elementData[size++] = e;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) elementData[index];
    }

    public int size() {
        return size;
    }

    public E set(int index, E element) {
        E oldValue = get(index);
        elementData[index] = element;
        return oldValue;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }

    public static void main(String[] args) {
        DynamicArray<Double> dynamicArray = new DynamicArray<>();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            dynamicArray.add((double) random.nextInt(100));
        }
        System.out.println(dynamicArray);
    }
}
