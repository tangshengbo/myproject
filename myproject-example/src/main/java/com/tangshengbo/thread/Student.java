package com.tangshengbo.thread;

import java.util.HashSet;

/**
 * Created by TangShengBo on 2017-05-17.
 */
public class Student {

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        Student student = (Student) obj;
        return student.age == this.age;
    }

    @Override
    public int hashCode() {
        return age * 100;
    }

    public static void main(String[] args) {
        Student student = new Student();
        student.setAge(10);

        Student student2 = new Student();
        student2.setAge(10);

        System.out.println(student.equals(student2));
        System.out.println(student.hashCode());
        System.out.println(student2.hashCode());

        String s1= "abc";
        String s2= "abc";
        System.out.println(s1.equals(s2));
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());

        HashSet<String> hSet=new HashSet<>();
        hSet.add(s1);
        hSet.add(s2);
        System.out.println(hSet.size());//输出1
    }
}
