package com.tangshengbo.design.oop.extent;

/**
 * Created by Tangshengbo on 2017/9/29.
 */
public class Daughter extends Father {

    @Override
    protected void init() {
        this.failCount = 0;
        this.successCount = 0;
    }

    @Override
    protected int getSuccessCount() {
        return successCount++;
    }

    @Override
    protected int getFailCount() {
        return this.failCount++;
    }
}
