package com.tangshengbo.thread.scheduled.callback;

import com.tangshengbo.thread.scheduled.MyCallable;

import java.util.concurrent.*;

/**
 * Created by Tangshengbo on 2017/8/21.
 */
public class FutureTaskTest {

    private final static ConcurrentMap<Object, Future<String>> taskCache = new ConcurrentHashMap<>();

    public static void main(String[] args) {
//        testFutureTask();
        executionTask("task");
    }

    private static void testFutureTask() {
        MyCallable myCallable1 = new MyCallable(100);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable........");
            }
        };

        FutureTask<Integer> futureTask1 = new FutureTask<>(myCallable1);
        FutureTask<String> futureTask2 = new FutureTask<>(runnable, "唐波");

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

    private static void executionTask(final String taskName) {
        while (true) {
            Future<String> future = taskCache.get(taskName); // 1.1,2.1
            if (future == null) {
                Callable<String> task = new Callable<String>() {
                    public String call() throws InterruptedException {
                        System.out.println("call taskName");
                        return taskName;
                    }
                };
                FutureTask<String> futureTask = new FutureTask<>(task);
                future = taskCache.putIfAbsent(taskName, futureTask); // 1.3
                if (future == null) {
                    future = futureTask;
                    futureTask.run(); // 1.4执行任务
                }
            }
            try {
                String result = future.get(); // 1.5,
                if (result != null) {
                    System.out.println(result);
                    break;
                }
            } catch (CancellationException e) {
                taskCache.remove(taskName, future);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
