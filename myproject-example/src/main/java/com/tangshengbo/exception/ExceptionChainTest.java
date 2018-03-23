package com.tangshengbo.exception;

import jodd.exception.ExceptionUtil;
import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * Created by TangShengBo on 2017-11-18.
 */
public class ExceptionChainTest {

    //底层测试函数
    public void func2() throws Exception {
        ExceptionUtil.throwRuntimeException(new Exception("func2 exception ...."));
    }

    //上层测试函数
    public void func1() throws Exception {
        try {
            func2();
        } catch (Exception ex) {
            ExceptionUtil.throwRuntimeException(new CustomException("func1 exception", ex));
        }
    }

    //客户端测试函数
    public static void main(String[] args) {
        ExceptionChainTest test = new ExceptionChainTest();
        try {
            test.func1();
        } catch (Exception e) {
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
    }
}
