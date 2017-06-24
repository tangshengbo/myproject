package com.tangshengbo.tutorial.socket;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by TangShengBo on 2017-06-24.
 */
public class UDPServer {

    public static void main(String[] args) {
        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, buf.length);
        try {
            DatagramSocket ds = new DatagramSocket(8687);
            System.out.println("UDP服务器启动......................");
            while (true) {
                ds.receive(dp);
                ByteArrayInputStream bais = new ByteArrayInputStream(buf);
                DataInputStream dis = new DataInputStream(bais);
                System.out.println(dis.readLong() + "\t" + dis.readInt());
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
