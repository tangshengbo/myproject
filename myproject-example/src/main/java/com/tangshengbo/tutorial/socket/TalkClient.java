package com.tangshengbo.tutorial.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TalkClient {

    private int port;

    private String host;

    public TalkClient(String host, int port) {
        this.port = port;
        this.host = host;
    }

    public void service() {
        Socket socket = null;
        try {
            socket = new Socket(host, port);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            System.out.println("欢迎来到Java Socket聊天室***********************");
            // 获取控制台输入的Scanner
            Scanner scanner = new Scanner(System.in);
            String accept;
            String send;
            while (true) {
                send = scanner.nextLine();
                dos.writeUTF(send);
                System.out.println("客户端:\t" + send);
                accept = dis.readUTF();
                System.out.println("服务器:\t" + accept);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        TalkClient talkClient = new TalkClient("127.0.0.1", 6662);
        talkClient.service();
    }
}
