package com.tangshengbo.design.state.work;

/**
 * Created by TangShengBo on 2017-09-28.
 */
public class WorkStateTest {

    public static void main(String[] args) {
        Work work = new Work(new ForenoonState());
        work.setHour(9);
        work.writeProgram();
        work.setHour(12);
        work.writeProgram();
        work.setHour(14);
        work.writeProgram();
        work.setHour(18);
        work.writeProgram();
        work.setHour(20);
        work.writeProgram();
        work.setFinish(Boolean.TRUE);
        work.setHour(9);
        work.writeProgram();
    }
}
