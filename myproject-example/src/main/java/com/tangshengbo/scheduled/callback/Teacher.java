package com.tangshengbo.scheduled.callback;

/**
 * Created by TangShengBo on 2017-07-03.
 */
public class Teacher implements  CallBack, Runnable {

    private Student student;

    public Teacher(Student student) {
        this.student = student;
    }

    private void publish() {
        System.out.println("老师发布作业................");
        student.doWork(this);
    }

    @Override
    public void run() {
       this.publish();
    }

    @Override
    public void advice(String name) {
        System.out.println(name + "：\t作业做完了.............");
    }
}
