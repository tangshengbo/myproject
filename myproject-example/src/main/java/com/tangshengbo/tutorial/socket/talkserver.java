package com.tangshengbo.tutorial.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TalkServer {

    public static void main(String args[]) {
        try {
            ServerSocket serverSocket = new ServerSocket(6662);
            System.out.println("服务器启动，等待客户端连接。。");
            Socket socket = serverSocket.accept();
            System.out.println("接收到请求:" + socket.getInputStream() + "\t" + socket.getInetAddress());
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("TalkClient:" + br.readLine());
            System.out.println("..............");
            DataInputStream inputTalk = new DataInputStream(System.in);
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            System.out.println("TalkServer:" + inputTalk.readUTF());
            pw.write(inputTalk.readUTF());
            pw.flush();
            pw.close();
            inputTalk.close();
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

}
