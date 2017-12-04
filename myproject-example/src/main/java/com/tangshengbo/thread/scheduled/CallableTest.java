package com.tangshengbo.thread.scheduled;

import jodd.util.ThreadUtil;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Tang on 2017/6/29.
 */
public class CallableTest {

    public static void main(String[] args) {
        try {
            completionServiceCount();
            Callable callable = Executors.callable(() -> {}, "callable");
            System.out.println(callable.call());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用completionService收集callable结果
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void completionServiceCount() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
        int threadNum = 10;
        for (int i = 0; i < threadNum; i++) {
            completionService.submit(getTask(i));
        }
        int sum = 0;
        int temp = 0;
        for (int i = 0; i < threadNum; i++) {
            //拿到一个已经完成的Callable并返回结果
            Future<Integer> future = completionService.take();
            try {
                temp = future.get(1, TimeUnit.MILLISECONDS);
            } catch (TimeoutException e) {
                future.cancel(true);
            }
            sum += temp;
            System.out.print(temp + "\t");
        }
        System.out.println("CompletionService all is : " + sum);
        executorService.shutdown();
    }

    public static Callable<Integer> getTask(final int no) {
        final Random rand = new Random();
        return () -> {
            int time = rand.nextInt(100) * 100;
            System.out.println("Thread:" + no + " time is:" + time);
            ThreadUtil.sleep(time);
            return no;
        };
    }
}
