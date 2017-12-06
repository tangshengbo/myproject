package com.tangshengbo.design.build;

/**
 * Created by Tangshengbo on 2017/10/12.
 */
public interface Builder {

    void buildPartA();

    void buildPartB();

    void buildPartC();

    Product getResult();
}
