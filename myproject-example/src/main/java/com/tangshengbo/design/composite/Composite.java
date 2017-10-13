package com.tangshengbo.design.composite;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Tangshengbo on 2017/10/13.
 */
public class Composite extends AbstractComponent {

    private List<Component> components = Lists.newArrayList();

    public Composite(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void remove(Component component) {
        components.remove(component);
    }

    @Override
    public void display(int depth) {
        System.out.println(String.format("%s--", name));
        for (Component component : components) {
            component.display(depth + 1);
        }
    }
}
