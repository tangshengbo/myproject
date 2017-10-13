package com.tangshengbo.design.composite;

/**
 * Created by Tangshengbo on 2017/10/13.
 */
public class Leaf extends AbstractComponent {

    public Leaf(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {

    }

    @Override
    public void remove(Component component) {

    }

    @Override
    public void display(int depth) {
        System.out.println(String.format("%s--", name));
    }
}
