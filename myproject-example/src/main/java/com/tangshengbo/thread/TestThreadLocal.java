package com.tangshengbo.thread;

import java.util.Random;

/**
 * Created by TangShengBo on 2017-05-17.
 */
public class TestThreadLocal implements Runnable {

    private ThreadLocal studentLocal = new ThreadLocal();

    public static void main(String[] args) {
        TestThreadLocal t = new TestThreadLocal();
        new Thread(t, "t1").start();
        new Thread(t, "t2").start();
    }

    @Override
    public void run() {
        accessStudent();
    }

    private void accessStudent() {
        Student s = this.getStudent();
        Random random = new Random();
        int age = random.nextInt(100);
        System.out.println("current thread set age " + Thread.currentThread().getName()
                + ":" + age);
        s.setAge(age);
        System.out.println("current thread first get age "
                + Thread.currentThread() + ":" + s.getAge());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(s + "\t" + Thread.currentThread().getName());
        System.out.println("current thread second get age "
                + Thread.currentThread() + ":" + s.getAge());
    }

    public Student getStudent() {
        Student s = (Student) studentLocal.get();
        if (s == null) {
            s = new Student();
            System.out.println(s + "\t" + Thread.currentThread().getName());
            studentLocal.set(s);
        }
        return s;
    }
}
