package com.tangshengbo.design.strategy;

/**
 * Created by Tangshengbo on 2017/9/30.
 */
public class CollectFeesFactory {

    public static CollectFees createCollectFees(String type) {
        switch (type) {
            case "正常收费":
                return new CollectFeesNormal();
            case "满300返100":
                return new CollectFeesReturn(300, 100);
            case "打8折":
                return new CollectFeesRebate(0.8);
        }
        return null;
    }

}
