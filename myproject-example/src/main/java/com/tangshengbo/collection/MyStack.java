package com.tangshengbo.collection;

import com.google.common.collect.Lists;
import com.tangshengbo.json.Account;

import java.util.LinkedList;

/**
 * Created by Tangshengbo on 2017/9/27.
 */
public class MyStack<T> {

    private final LinkedList<T> LINKED_LIST = Lists.newLinkedList();

    public void push(T t) {
        LINKED_LIST.push(t);
    }

    public T pop() {
        return LINKED_LIST.pop();
    }

    public T peek() {
        return LINKED_LIST.getLast();
    }

    public static void main(String[] args) {
        //在声明这个Stack的时候，使用泛型<Paper>就表示该Stack只能放Paper
        MyStack<Paper> heroStack = new MyStack<>();
        heroStack.push(new Paper());
        //不能放Item
        heroStack.push(new Paper());

        //在声明这个Stack的时候，使用泛型<Account>就表示该Stack只能放Account
        MyStack<Account> itemStack = new MyStack<>();
        itemStack.push(new Account());
        //不能放Hero
        itemStack.push(new Account());
    }
}
