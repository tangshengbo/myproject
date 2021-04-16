package com.tangshengbo.thread.forkjoin;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;


public class CountTask extends RecursiveTask<Integer> {

    private static final Logger logger = LoggerFactory.getLogger(CountTask.class);

    // 阈值
    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        System.out.println(Thread.currentThread().getName() + "- compute");
        if (this.isCompletedAbnormally()) {
            System.out.println(ExceptionUtils.getStackTrace(this.getException()));
        }

        int sum = 0;
        // 如果任务足够小就计算任务
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            System.out.println("=====任务分解======");
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            System.out.println(String.format("middle %s", middle));
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            // 执行子任务
            leftTask.fork();
            rightTask.fork();
            // 等待子任务执行完，并得到其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        StopWatch watch = new StopWatch();
        watch.start("ForkJoinPool");
        ForkJoinPool forkJoinPool = new ForkJoinPool(5);
        // 生成一个计算任务，负责计算1+2+3+4
        CountTask task = new CountTask(1, 100);
        // 执行一个任务
        Future<Integer> result = forkJoinPool.submit(task);
        Integer resultNum = null;
        try {
            resultNum = result.get();
        } catch (Exception e) {
            System.out.println(e);
        }
        watch.stop();
        logger.info("result1:{} time:{}", resultNum, watch.prettyPrint());

    }
}
