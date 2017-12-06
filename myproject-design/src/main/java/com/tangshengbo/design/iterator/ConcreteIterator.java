package com.tangshengbo.design.iterator;

import java.util.List;

/**
 * Created by Tangshengbo on 2017/9/30.
 */
public class ConcreteIterator<E> implements Iterator<E> {

    private List<E> list;

    private int cursor = 0;

    public ConcreteIterator(List<E> list) {
        this.list = list;
    }

    @Override
    public E next() {
        E obj = null;
        if (this.hasNext()) {
            obj = this.list.get(cursor++);
        }
        return obj;
    }

    @Override
    public boolean hasNext() {
        if (cursor == list.size()) {
            return false;
        }
        return true;
    }
}
