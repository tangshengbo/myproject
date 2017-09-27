package com.tangshengbo.exception;

/**
 * Created by Tangshengbo on 2017/9/27.
 */
public class Calc {

    public void calc(int num) {
        if (num == 0) {
            throw new MyException("除数不能为0");
        }
    }
}
