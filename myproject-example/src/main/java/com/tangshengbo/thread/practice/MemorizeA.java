package com.tangshengbo.thread.practice;

import jodd.util.ThreadUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by TangShengBo on 2017/11/30.
 */
public class MemorizeA<A, V> implements Computable<A, V> {

//    @GuardedBy("this")
//    private final Map<A, V> cache = new HashMap<>();

    private final Map<A, V> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public MemorizeA(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(A arg) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        ThreadUtil.sleep(1000);
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }

    public static void main(String[] args) {
        Computable<String, Integer> computable = (s) -> {
            System.out.println("lambada" + s);
            ThreadUtil.sleep(1000);
            return s.hashCode();
        };
        MemorizeA<String, Integer> memorizeA = new MemorizeA<>(computable);
        Runnable r1 = () -> {
            try {
                System.out.println(memorizeA.compute(""));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(r1, "thread-" + i).start();
        }
    }
}

