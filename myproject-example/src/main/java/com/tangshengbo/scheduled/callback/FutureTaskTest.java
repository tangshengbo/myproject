package com.tangshengbo.scheduled.callback;

import com.tangshengbo.scheduled.MyCallable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by Tangshengbo on 2017/8/21.
 */
public class FutureTaskTest {

    public static void main(String[] args) {
        MyCallable myCallable1 = new MyCallable(100);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable........");
            }
        };

        FutureTask futureTask1 = new FutureTask(myCallable1);
        FutureTask futureTask2 = new FutureTask(runnable, "唐波");

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(futureTask1);
        executor.execute(futureTask2);
        try {
            while (true) {
                if (futureTask1.isDone() && futureTask2.isDone()) {
                    System.out.println("futureTask1:\t" + futureTask1.get());
                    System.out.println("futureTask2:\t" + futureTask2.get());
                    System.out.println("Done........");
                    executor.shutdown();
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
