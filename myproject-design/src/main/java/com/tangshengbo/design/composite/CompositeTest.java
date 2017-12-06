package com.tangshengbo.design.composite;

/**
 * Created by Tangshengbo on 2017/10/13.
 */
public class CompositeTest {

    public static void main(String[] args) {
        Composite root = new Composite("root");
        root.add(new Leaf("Leaf A"));
        root.add(new Leaf("Leaf B"));

        Composite left = new Composite("left");
        left.add(new Leaf("Leaf left A"));
        left.add(new Leaf("Leaf left B"));

        Composite right = new Composite("right");
        right.add(new Leaf("Leaf right A"));
        right.add(new Leaf("Leaf right B"));

        root.add(left);
        root.add(right);

        root.display(1);
    }
}
