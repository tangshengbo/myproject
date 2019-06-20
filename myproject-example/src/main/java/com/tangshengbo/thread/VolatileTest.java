package com.tangshengbo.thread;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by TangShengBo on 2017-05-16.
 */
public class VolatileTest {

    private static final int THREAD_COUNT = 20;

    public static volatile int race = 0;

    static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void increase() {
        atomicInteger.getAndIncrement();
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.tangshengbo.innerclass.Code", true, Thread.currentThread().getContextClassLoader());
            System.out.println(Thread.currentThread().getContextClassLoader());
            System.out.println(ClassLoader.getSystemClassLoader().getParent());
//            ClassLoader.class.getClassLoader().loadClass("com.tangshengbo.innerclass.Code");
//            Thread.currentThread().getContextClassLoader().loadClass("com.tangshengbo.innerclass.Code");
//            Thread.currentThread().setContextClassLoader();


            Scanner scan = new Scanner(System.in);
            List<Integer> list = new ArrayList<>();
            list.stream().sorted(Comparator.reverseOrder());

            //用户输入10个整数
            System.out.println("请输入10个整数：");
            for(int i = 0; i < 10; i++)
            {
                list.add(scan.nextInt());
            }
            scan.close();

            //排序
            Collections.sort(list, Collections.reverseOrder());

            //输出排序结果
            System.out.println(list);



        } catch (Exception e) {
            e.printStackTrace();
        }
//        for (int J = 0; J < 10; J++) {
//            atomicInteger.set(0);
//            Thread[] threads = new Thread[THREAD_COUNT];
//            for (Thread thread : threads) {
//                thread = new Thread(() -> {
//                    for (int i = 0; i < 500; i++) {
//                        increase();
//                    }
//                });
//                thread.start();
//            }
//            ThreadUtil.sleep(1000);
//            System.out.println(atomicInteger.get());
//        }
    }

}
