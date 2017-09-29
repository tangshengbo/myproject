package com.tangshengbo.design.state.work;

/**
 * Created by Tangshengbo on 2017/9/28.
 * 强制下班状态
 */
public class ForceOffWorkState implements State {

    @Override
    public void writeProgram(Work work) {
        System.out.println(String.format("当前时间:%s点 老板规定20点之前必须下班回家!....", work.getHour()));
    }
}
