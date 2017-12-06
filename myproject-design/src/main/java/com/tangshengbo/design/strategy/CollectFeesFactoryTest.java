package com.tangshengbo.design.strategy;

/**
 * Created by Tangshengbo on 2017/9/30.
 */
public class CollectFeesFactoryTest {

    public static void main(String[] args) {
        CollectFees collectFees = CollectFeesFactory.createCollectFees("打8折");
        System.out.println(String.format("总计：%s", collectFees.acceptCash(100)));
    }
}
