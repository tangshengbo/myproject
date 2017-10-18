package com.tangshengbo.design.visitor.example;

/**
 * Created by Tangshengbo on 2017/10/18.
 */
public interface Action {

    String getManConclusion(Man man);

    String getWomanConclusion(Woman woman);
}
