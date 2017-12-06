package com.tangshengbo.design.oop.extent;

/**
 * Created by Tangshengbo on 2017/9/29.
 */
public abstract class Father {

    protected int successCount;

    protected int failCount;

    protected void flowPath() {
        init();
        System.out.println(String.format("成功：%s 失败：%s", getSuccessCount(), getFailCount()));
    }

    protected abstract void init();

    protected int getSuccessCount(){
        return failCount;
    }

    protected int getFailCount() {
        return successCount;
    }
}
