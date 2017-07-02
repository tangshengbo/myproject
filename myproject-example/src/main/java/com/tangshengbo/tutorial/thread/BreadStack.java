package com.tangshengbo.tutorial.thread;

/**
 * Created by TangShengBo on 2017-07-02.
 */
public class BreadStack {

    private int index = 0;

    private Bread[] breads = new Bread[10];

    public synchronized void push(Bread bread) {
        while (index == breads.length) {
            try {
                //当前执行的线程进入等待状态
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        breads[index] = bread;
        index++;
    }

    public synchronized Bread pop() {
        while (index == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //唤醒该对象上等待的线程（调用了wait方法的线程）
        this.notify();
        index--;
        return breads[index];
    }

}
