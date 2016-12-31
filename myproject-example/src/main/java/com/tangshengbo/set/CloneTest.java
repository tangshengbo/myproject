package com.tangshengbo.set;

/**
 * Created by Administrator on 2016/11/30.
 */
public class CloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {

        Paper paper = new Paper();
        Paper clonePaper = (Paper) paper.clone();

        System.out.println(clonePaper.getFlag());
    }
}
