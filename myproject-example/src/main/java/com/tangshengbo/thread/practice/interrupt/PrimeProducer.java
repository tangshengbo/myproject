package com.tangshengbo.thread.practice.interrupt;

import jodd.util.ThreadUtil;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by TangShengBo on 2017/12/5.
 */
public class PrimeProducer extends Thread {

    private final BlockingQueue<BigInteger> queue;

    private PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted())
                queue.put(p = p.nextProbablePrime());
        } catch (InterruptedException consumed) {
            /* Allow thread to exit */
            System.out.println(ExceptionUtils.getStackTrace(consumed));
        }
    }

    public void cancel() {
        interrupt();
    }

    public static void main(String[] args) {
        PrimeProducer primeProducer = new PrimeProducer(new LinkedBlockingQueue<>());
        primeProducer.start();
        ThreadUtil.sleep(2000);
        primeProducer.cancel();
    }
}
