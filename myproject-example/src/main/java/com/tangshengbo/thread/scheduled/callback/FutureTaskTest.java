package com.tangshengbo.thread.scheduled.callback;

import com.tangshengbo.thread.scheduled.MyCallable;
import jodd.util.ThreadUtil;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Tangshengbo on 2017/8/21.
 */
public class FutureTaskTest {

    private final static ConcurrentMap<Object, Future<String>> taskCache = new ConcurrentHashMap<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        testFutureTask();
        int taskSize = 100;
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
// 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            Callable c = new MyCallable(i );
// 执行任务并获取 Future 对象
            Future f = pool.submit(c);
            list.add(f);
        }
// 关闭线程池
        pool.shutdown();
// 获取所有并发任务的运行结果
        for (Future f : list) {
// 从 Future 对象上获取任务的返回值，并输出到控制台
            System.out.println("res： " + f.get());
        }




//        executionTask("task");
    }

    private static void testFutureTask() {
        MyCallable myCallable1 = new MyCallable(100);
        Runnable runnable = () -> System.out.println("Runnable........");

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
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void executionTask(final String taskName) {
        while (true) {
            Future<String> future = taskCache.get(taskName); // 1.1,2.1
            if (future == null) {
                Callable<String> task = () -> {
                    ThreadUtil.sleep(3000);
                    System.out.println("call taskName");
                    return taskName;
                };
                FutureTask<String> futureTask = new FutureTask<>(task);
                future = taskCache.putIfAbsent(taskName, futureTask); // 1.3
                if (future == null) {
                    future = futureTask;
                    futureTask.run(); // 1.4执行任务
                }
            }
            try {
                String result = future.get(10, TimeUnit.NANOSECONDS);
                if (result != null) {
                    System.out.println(result);
                    break;
                }
            } catch (CancellationException e) {
                taskCache.remove(taskName, future);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                if (e instanceof TimeoutException) {
                    System.out.println(ExceptionUtils.getStackTrace(e));
                    future.cancel(true);
                }
            }
        }
    }
}
