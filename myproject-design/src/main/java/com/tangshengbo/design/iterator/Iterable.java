package com.tangshengbo.design.iterator;

/**
 * Created by Tangshengbo on 2017/9/30.
 */
public interface Iterable<E> {

    void add(E obj);

    void remove(E obj);

    Iterator<E> iterator();
}
