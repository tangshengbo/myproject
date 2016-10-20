package com.tangshengbo.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(9992);
			
			System.out.println("服务器启动，等待客户端连接。。");
			ExecutorService executorService = Executors.newCachedThreadPool();
			int count = 0;
			while (true) {
				Socket socket = serverSocket.accept();
				Runnable socketThread = new SocketThread(socket);
				executorService.execute(socketThread);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
