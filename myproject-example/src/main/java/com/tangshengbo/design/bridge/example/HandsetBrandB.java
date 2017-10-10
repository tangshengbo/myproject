package com.tangshengbo.design.bridge.example;

/**
 * Created by Tangshengbo on 2017/10/10.
 */
public class HandsetBrandB extends HandsetBrand {

    public HandsetBrandB(HandsetSoft handsetSoft) {
        super(handsetSoft);
    }

    @Override
    protected void run() {
        handsetSoft.run();
    }
}
