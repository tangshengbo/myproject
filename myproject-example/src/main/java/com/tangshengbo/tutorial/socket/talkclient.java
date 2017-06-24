package com.tangshengbo.tutorial.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TalkClient {

    public static void main(String args[]) {
        try {
            Socket socket = new Socket("127.0.0.1", 6662);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            String talk = "";
            talk = br.readLine();
            while (!"exit".equals(talk)) {
                System.out.println("TalkClient:" + talk);
                pw.write(talk);
                pw.flush();
            }
            pw.close();
            br.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
