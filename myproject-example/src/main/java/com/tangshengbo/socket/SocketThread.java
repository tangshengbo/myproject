package com.tangshengbo.socket;

import com.tangshengbo.io.Student;

import java.io.*;
import java.net.Socket;


public class SocketThread implements Runnable {

    private Socket socket;

    public SocketThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
//        Lock lock = new ReentrantLock();
//        lock.lock();
        try {
            Thread.sleep(2);
//            System.out.println("获得锁" + lock.tryLock());

            InputStream is = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(is);


            Student student = (Student) objectInputStream.readObject();
            System.out.println(student.toString());

            socket.shutdownInput();

            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("服务端收到你" + "" + "可以登录.................." + Thread.currentThread().getName());

            pw.close();
            pw.flush();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } finally {
            try {
                socket.shutdownOutput();
                socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally {
//                lock.unlock();
            }

        }

    }

}
