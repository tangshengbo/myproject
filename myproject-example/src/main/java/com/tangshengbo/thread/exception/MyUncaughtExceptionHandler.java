package com.tangshengbo.thread.exception;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * Created by Tangshengbo on 2017/11/10.
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("线程：" + t.getName() + " 出现异常：" + ExceptionUtils.getStackTrace(e));
    }
}
