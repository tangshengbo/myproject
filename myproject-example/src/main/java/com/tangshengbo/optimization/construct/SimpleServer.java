package com.tangshengbo.optimization.construct;

import com.tangshengbo.arithmetic.Student;

/**
 * Created by Tangshengbo on 2018/1/2.
 */
public class SimpleServer extends Server implements Cloneable {

    private int port;

    private Student student;

    {
        student = new Student();
        System.out.println("构造块初始化.......");
        port = 2000;
    }

    public SimpleServer(int port) {
        System.out.println("有参构造函数初始化.......");
        this.port = port;
    }

    public Student getStudent() {
        return student;
    }

    public SimpleServer() {
        System.out.println("无参构造函数初始化.......");
    }

    @Override
    public int getPort() {
        synchronized (this) {
            return port > 100 ? port : DEFAULT_PORT;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //浅拷贝
        SimpleServer simpleServer = (SimpleServer) super.clone();
        //深拷贝
        simpleServer.student = (Student) this.student.clone();
        return simpleServer;
    }
}
