package com.tangshengbo.thread.practice.interrupt;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by TangShengBo on 2017/12/5.
 */
public class ReaderThread extends Thread {

    private static final int BUFSZ = 512;
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    /**
     * 通过关闭套接字来关闭不可中断的阻塞操作
     * read()返回-1
     */
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException ignored) {
        } finally {
            super.interrupt();
        }
    }

    public void run() {
        try {
            byte[] buf = new byte[BUFSZ];
            while (true) {
                int count = in.read(buf);
                if (count < 0)
                    break;
                else if (count > 0)
                    processBuffer(buf, count);
            }
        } catch (IOException e) { /* Allow thread to exit */
        }
    }

    public void processBuffer(byte[] buf, int count) {
    }
}
