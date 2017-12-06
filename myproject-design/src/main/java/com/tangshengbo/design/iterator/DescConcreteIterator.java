package com.tangshengbo.design.iterator;

import java.util.List;

/**
 * Created by Tangshengbo on 2017/9/30.
 */
public class DescConcreteIterator<E> implements Iterator<E> {

    private List<E> list;

    private int cursor = 0;

    public DescConcreteIterator(List<E> list) {
        this.list = list;
        cursor = list.size() - 1;
    }

    @Override
    public E next() {
        E obj = null;
        if (this.hasNext()) {
            obj = list.get(cursor--);
        }
        return obj;
    }

    @Override
    public boolean hasNext() {
        if (cursor >= 0) {
            return true;
        }
        return false;
    }
}
