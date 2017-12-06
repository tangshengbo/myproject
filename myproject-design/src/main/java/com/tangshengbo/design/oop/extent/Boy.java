package com.tangshengbo.design.oop.extent;

/**
 * Created by Tangshengbo on 2017/9/29.
 */
public class Boy extends Father {


    @Override
    protected void init() {
      this.successCount = 0;
    }

    @Override
    protected int getSuccessCount() {
        return this.successCount = 100;
    }

    @Override
    protected int getFailCount() {
        return this.failCount++;
    }
}
