package com.tangshengbo.design.state.work;

/**
 * Created by TangShengBo on 2017-09-27.
 * 上午和中午工作状态
 */
public class ForenoonState implements State {

    @Override
    public void writeProgram(Work work) {
        double hour = work.getHour();
        if (hour < 12) {
            System.out.println(String.format("当前时间:%s点 上午工作,精神百倍", hour));
            return;
        }
        //超过12点转入中午工作状态
        work.setCurrent(new NoonState());
        work.writeProgram();
    }
}
