package com.tangshengbo.design.state.work;

/**
 * Created by TangShengBo on 2017-09-27.
 */
public class NoonState implements  State {

    @Override
    public void writeProgram(Work work) {
        double hour = work.getHour();
        if (hour < 13) {
            System.out.println(String.format("当前时间:%s点 午饭,犯困,午休", hour));
            return;
        }
        //超过13点转入下午工作状态
        work.setCurrent(new AfternoonState());
        work.writeProgram();
    }
}
