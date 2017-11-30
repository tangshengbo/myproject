package com.tangshengbo.thread.practice;

import jodd.util.RandomStringUtil;
import jodd.util.ThreadUtil;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.util.concurrent.*;

/**
 * Created by TangShengBo on 2017/11/30.
 */
public class MemorizeB<A, V> implements Computable<A, V> {

    private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public MemorizeB(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(final A arg) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        ThreadUtil.sleep(1000);
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> eval = () -> c.compute(arg);
            FutureTask<V> ft = new FutureTask<>(eval);
            f = cache.putIfAbsent(arg, ft);
            if (f == null) {
                f = ft;
                ft.run();
            }
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    public static void main(String[] args) {
        Computable<String, Future<Integer>> computable = (s) -> {
            System.out.println("lambada\t" + s);
            ThreadUtil.sleep(1000);
            Callable<Integer> eval = s::hashCode;
            return new FutureTask<>(eval);
        };

        MemorizeB<String,Future<Integer>> memorizeB = new MemorizeB<>(computable);
        Runnable r1 = () -> {
            try {
                memorizeB.compute(RandomStringUtil.randomAlphaNumeric(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            service.execute(r1);
        }
        service.shutdown();
    }
}
