package com.tangshengbo.socket;

import com.tangshengbo.io.Student;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

public class Client {
	
	public static void main(String[] args) {

		try {
			long start = System.currentTimeMillis(); // 获取开始时间6588ms
			LinkedList linkedList = new  LinkedList();

			for (int i = 0; i < 200000; i++) {
				Socket socket;
				byte b = 122;

				socket = new Socket("127.0.0.1", 9992);
				OutputStream os = socket.getOutputStream();
				Student student = new Student("00000001", "唐声波", 12);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
				objectOutputStream.writeObject(student);
				System.out.println("发送至server端");
				socket.shutdownOutput();

				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader bfr = new BufferedReader(isr);
				String result = null;

				while ((result = bfr.readLine()) != null) {
					System.out.println("收到Server结果" + result);
				}
				socket.close();

			}
			long end = System.currentTimeMillis(); // 获取结束时间
			System.out.println("程序运行时间： " + (end - start) + "ms");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
