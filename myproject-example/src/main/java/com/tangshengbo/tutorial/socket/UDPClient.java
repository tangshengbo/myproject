package com.tangshengbo.tutorial.socket;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * Created by TangShengBo on 2017-06-24.
 */
public class UDPClient {

    public static void main(String[] args) {
        byte[] buf = "hello".getBytes();
        long num = 10000L;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        int location = 200;
        String name = "唐声波";
        try {
            dos.writeLong(num);
            dos.writeInt(location);
            dos.write(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }

        buf = baos.toByteArray();
        DatagramPacket dp = new DatagramPacket(buf, buf.length, new InetSocketAddress("127.0.0.1", 8687));
        try {
            DatagramSocket ds = new DatagramSocket(8745);
            for (int i = 0; i < 100; i++) {
                ds.send(dp);
            }
            System.out.println("消息已发送.................");
            ds.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
