package com.tangshengbo.design.state.work;

/**
 * Created by TangShengBo on 2017-09-27.
 */
public class AfternoonState implements State {

    @Override
    public void writeProgram(Work work) {
        double hour = work.getHour();
        if (hour < 17) {
            System.out.println(String.format("当前时间:%s点 下午状态还不错,继续努力", hour));
            return;
        }
        //超过17点转入傍晚工作状态
        work.setCurrent(new EveningState());
        work.writeProgram();
    }
}
