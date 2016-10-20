package com.tangshengbo.socket;

import com.tangshengbo.io.Student;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class SocketThread implements Runnable {
	private Socket socket;
	
	private Lock lock = new ReentrantLock();

	public SocketThread(Socket socket) {
		this.socket = socket;
	}


	public  void run() {

			try {
				Thread.sleep(2);
				lock.lock();
				System.out.println("获得锁"+lock.tryLock());
				InputStream is = socket.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(is);
				try {
					Student student = (Student)objectInputStream.readObject();
					System.out.println(student.toString());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				socket.shutdownInput();
				OutputStream os = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(os);
				pw.write("服务端收到你唐波可以登录.................." + Thread.currentThread().getName());
				pw.flush();
				

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				
				try {
					socket.shutdownOutput();
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lock.unlock();
			}

		
	}

}
