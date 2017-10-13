package com.tangshengbo.design.composite;

/**
 * Created by Tangshengbo on 2017/10/13.
 */
public interface Component {

    void add(Component component);

    void remove(Component component);

    void display(int depth);
}
