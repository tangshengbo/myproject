package com.tangshengbo.design.strategy;

/**
 * Created by Tangshengbo on 2017/9/30.
 */
public class StrategyTest {

    public static void main(String[] args) {
        CollectFeesContext collectFeesNormal = new CollectFeesContext(new CollectFeesNormal());
        CollectFeesContext collectFeesReturn = new CollectFeesContext(new CollectFeesReturn(50, 20));
        CollectFeesContext CollectFeesRebate = new CollectFeesContext(new CollectFeesRebate(0.8));

        System.out.println(String.format("总计：%s",collectFeesNormal.acceptCash(100)));
        System.out.println(String.format("总计：%s",collectFeesReturn.acceptCash(100)));
        System.out.println(String.format("总计：%s",CollectFeesRebate.acceptCash(100)));
    }
}
