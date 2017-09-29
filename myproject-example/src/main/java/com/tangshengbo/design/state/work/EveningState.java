package com.tangshengbo.design.state.work;

/**
 * Created by TangShengBo on 2017-09-27.
 */
public class EveningState implements State {

    @Override
    public void writeProgram(Work work) {
        if (work.isFinish()) {
            //如果完成任务
            work.setCurrent(new RestState());
            work.writeProgram();
            return;
        }
        double hour = work.getHour();
        if (hour < 20) {
            System.out.println(String.format("当前时间:%s点 加班哦,疲惫之极", hour));
            return;
        }
        work.setCurrent(new ForceOffWorkState());
        work.writeProgram();
    }
}
