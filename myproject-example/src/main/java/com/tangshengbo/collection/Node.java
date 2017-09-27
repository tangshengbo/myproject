package com.tangshengbo.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tangshengbo on 2017/9/27.
 * 实现一个二叉树，并遍历
 */
public class Node {

    private Node leftNode;
    private Node rightNode;
    private Object value;

    public void add(Object obj) {
        if (value == null) {
            value = obj;
        } else {
            if ((Integer) (obj) < (Integer) (value)) {
                if (leftNode == null) {
                    leftNode = new Node();
                }
                leftNode.add(obj);
            } else {
                if (rightNode == null) {
                    rightNode = new Node();
                }
                rightNode.add(obj);
            }
        }
    }

    public List<Object> values() {
        List<Object> values = new ArrayList<>();
        if (null != leftNode) {
            values.addAll(leftNode.values());
        }
        values.add(value);
        if (null != rightNode) {
            values.addAll(rightNode.values());
        }
        return values;
    }

    public static void main(String[] args) {
        int randoms[] = new int[]{67, 7, 30, 73, 10, 0, 78, 81, 10, 74};
        Node roots = new Node();
        for (int number : randoms) {
            roots.add(number);
        }
        System.out.println(roots.values());
    }
}
