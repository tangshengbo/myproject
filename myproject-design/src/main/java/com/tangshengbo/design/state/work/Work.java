package com.tangshengbo.design.state.work;

/**
 * Created by TangShengBo on 2017-09-27.
 */
public class Work {

    private State current;

    private double hour;

    private boolean finish;

    public Work(State state) {
        this.current = state;
    }

    public State getCurrent() {
        return current;
    }

    public double getHour() {
        return hour;
    }

    public void setHour(double hour) {
        this.hour = hour;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public void setCurrent(State current) {
        this.current = current;
    }

    public void writeProgram() {
        current.writeProgram(this);
    }

}
