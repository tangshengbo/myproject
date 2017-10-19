package com.tangshengbo.design.oop.extent;

/**
 * Created by Tangshengbo on 2017/9/29.
 */
public class ExtentTest {

    public static void main(String[] args) {
//        Boy boy = new Boy();
//
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                boy.flowPath();
//            }
//        };
//
//        Thread thread = new Thread(runnable);
//        thread.start();
//
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        Thread thread2 = new Thread(runnable);
//        thread2.start();
//
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Daughter daughter = new Daughter();
        daughter.flowPath();
        daughter.flowPath();

        testExtentField();
    }

    private static void testExtentField() {
        ParentClass parentClass = new ParentClass();
        SubClass subClass =  new SubClass();
        subClass.init();
        System.out.println(parentClass.i + "\t" + subClass.i);
        System.out.println(parentClass.string + "\t" + subClass.string);

    }
}
