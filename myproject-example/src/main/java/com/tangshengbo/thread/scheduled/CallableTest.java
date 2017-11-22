package com.tangshengbo.thread.scheduled;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Tang on 2017/6/29.
 */
public class CallableTest {

    public static void main(String[] args) {
        try {
            completionServiceCount();
            Callable callable = Executors.callable(new Runnable() {
                @Override
                public void run() {

                }
            }, "tangshengbo");

            System.out.println(callable.call());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
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
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(
                executorService);
        int threadNum = 10;
        for (int i = 0; i < threadNum; i++) {
            completionService.submit(getTask(i));
        }
        int sum = 0;
        int temp;
        for (int i = 0; i < threadNum; i++) {
            //拿到一个已经完成的Callable并返回结果
            temp = completionService.take().get();
            sum += temp;
            System.out.print(temp + "\t");
        }
        System.out.println("CompletionService all is : " + sum);
        executorService.shutdown();
    }

    public static Callable<Integer> getTask(final int no) {
        final Random rand = new Random();
        Callable<Integer> task = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int time = rand.nextInt(100) * 100;
                System.out.println("Thread:" + no + " time is:" + time);
                Thread.sleep(time);
                return no;
            }
        };
        return task;
    }
}