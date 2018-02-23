package com.tangshengbo.net.address;

import java.util.Random;

/**
 * Created by TangShengBo on 2018/2/12.
 */
public class Student {

    private String name;
    private int age;

    private String name2;
    private int age2;

    public Student() {
    }

    public Student(String name, int age, String name2, int age2) {
        this.name2 = name2;
        this.age2 = age2;
    }

    public static void main(String[] args) {
        int[] a = new int[20];
        Random rand = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = rand.nextInt(100);
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = 1; j < a.length - i; j++) {
                System.out.println(a.length - i);
                if (a[j - 1] > a[j]) {
                    int temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                }
            }
        }

        for (Integer i : a) {
            System.out.println(i);
        }
        
    }
}
