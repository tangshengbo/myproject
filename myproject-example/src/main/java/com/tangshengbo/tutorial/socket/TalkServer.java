package com.tangshengbo.tutorial.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TalkServer {

    private int port;

    public TalkServer(int port) {
        this.port = port;
    }

    public TalkServer() {
    }

    public void service() {
        Socket socket = null;
        try {
            //建立服务器连接
            ServerSocket serverSocket = new ServerSocket(port);
            //等待用户连接
            System.out.println("欢迎来到Java Socket聊天室***********************");
            socket = serverSocket.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            // 获取控制台输入的Scanner
            Scanner scanner = new Scanner(System.in);
            String accept;
            String send;
            while (true) {
                accept = dis.readUTF();
                System.out.println("客户端:\t" + accept);
                send = scanner.nextLine();
                dos.writeUTF(send);
                System.out.println("服务器:\t" + send);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        TalkServer talkServer = new TalkServer(6662);
        talkServer.service();
    }

}
