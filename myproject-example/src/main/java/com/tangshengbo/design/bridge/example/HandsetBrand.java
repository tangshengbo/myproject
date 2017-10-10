package com.tangshengbo.design.bridge.example;

/**
 * Created by Tangshengbo on 2017/10/10.
 */
public abstract class HandsetBrand {

    protected HandsetSoft handsetSoft;

    public HandsetBrand(HandsetSoft handsetSoft) {
        this.handsetSoft = handsetSoft;
    }

    protected abstract void run();
}
