package com.tangshengbo.thread.practice;

/**
 * Created by TangShengBo on 2017/11/30.
 */
@FunctionalInterface
public interface Computable<A, V> {

    V compute(A arg) throws InterruptedException;
}
