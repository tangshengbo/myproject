package com.tangshengbo.thread.scheduled;

import jodd.util.ThreadUtil;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.util.concurrent.*;

/**
 * Created by Tangshengbo on 2017/12/22.
 */
public class CalculatorTask implements Callable<Integer> {

    private int seedMoney;

    public CalculatorTask(int seedMoney) {
        this.seedMoney = seedMoney;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        ThreadUtil.sleep(5000);
        return seedMoney / 10;
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Integer> futureTask = service.submit(new CalculatorTask(5000));
        while (!futureTask.isDone()) {
            ThreadUtil.sleep(500);
            System.out.print("#");
        }
        System.out.println();
        try {
            System.out.println("计算完成 税金是：" + futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
        service.shutdown();
    }
}
