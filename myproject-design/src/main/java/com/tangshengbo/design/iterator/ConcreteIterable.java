package com.tangshengbo.design.iterator;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Tangshengbo on 2017/9/30.
 */
public class ConcreteIterable<E> implements Iterable<E> {

    private List<E> objects = Lists.newArrayList();

    @Override
    public void add(E obj) {
        objects.add(obj);
    }

    @Override
    public void remove(E obj) {
        objects.remove(obj);
    }

    @Override
    public Iterator<E> iterator() {
//        return new ConcreteIterator<>(objects);
        return new DescConcreteIterator<>(objects);
    }
}
