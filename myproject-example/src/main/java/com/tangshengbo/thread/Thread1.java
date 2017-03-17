package com.tangshengbo.thread;

public class Thread1 implements Runnable {

    public void run() {

        synchronized (this) {

            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
            }
        }

    }

    public synchronized void fixed() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
        }
    }

    public void queue() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
        }


    }

    public static void main(String[] args) {

        Thread1 t1 = new Thread1();
        Thread threadT1 = new Thread(t1, "T1");

        Thread threadT2 = new Thread(t1, "T2");

//		Thread threadT3 = new Thread(new Runnable() {  public void run() {  t1.queue(); } },"T3");
//
//		Thread threadT4 = new Thread(new Runnable() {  public void run() {  t1.fixed(); } },"T4");

//		threadT1.start();
//		threadT2.start();
//		threadT3.start();
//		threadT4.start();

    }

}

