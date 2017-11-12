package com.tangshengbo.thread.util;

import jodd.util.ThreadUtil;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by TangShengBo on 2017-11-12.
 */
public class ExchangerTest {

    private static final Exchanger<String> exchanger = new Exchanger<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //A录入银行流水数据
                    String A = "银行流水A";
                    ThreadUtil.sleep(1000);
                    String B = exchanger.exchange(A);
                    System.out.println("B:" + B);
                } catch (InterruptedException e) {
                    System.out.println(ExceptionUtils.getStackTrace(e));
                }
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //B录入银行流水数据
                    String B = "银行流水B";
                    String A = exchanger.exchange(B);
                    System.out.println("A:" + A);
                    ThreadUtil.sleep(1000);
                    System.out.println("A和B数据是否一致：" + A.equals(B) + "，A录入的是："
                            + A + "，B录入是：" + B);
                } catch (InterruptedException e) {
                    System.out.println(ExceptionUtils.getStackTrace(e));
                }
            }
        });
        threadPool.shutdown();
    }
}
