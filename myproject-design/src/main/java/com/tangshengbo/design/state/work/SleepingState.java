package com.tangshengbo.design.state.work;

/**
 * Created by TangShengBo on 2017-09-28.
 */
public class SleepingState implements State {

    @Override
    public void writeProgram(Work work) {
        System.out.println(String.format("当前时间:%s点 不行了,要睡着了!....", work.getHour()));
    }
}
