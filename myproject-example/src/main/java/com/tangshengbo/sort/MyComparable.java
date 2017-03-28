package com.tangshengbo.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by tangshengbo on 2017/3/23.
 */
public class MyComparable implements Comparable<MyComparable> {

    private int count;

    public MyComparable(int count) {
        this.count = count;
    }

    public MyComparable() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int compareTo(MyComparable o) {
        return o.getCount() - this.getCount();
    }

    public static void main(String[] args) {
        List<MyComparable> comparables = new ArrayList<>();
        MyComparable comparable = new MyComparable(1);
        MyComparable comparable2 = new MyComparable(2);
        MyComparable comparable3 = new MyComparable(3);
        MyComparable comparable4 = new MyComparable(4);
        MyComparable comparable5 = new MyComparable(5);
        comparables.add(comparable);
        comparables.add(comparable4);
        comparables.add(comparable3);
        comparables.add(comparable2);
        comparables.add(comparable5);

        System.out.println("No sort ========================================");
        for (MyComparable myComparable : comparables) {
            System.out.println(myComparable.getCount());
        }

        Collections.sort(comparables);
        System.out.println("Collections sort ========================================");
        for (MyComparable myComparable : comparables) {
            System.out.println(myComparable.getCount());
        }
    }
}
