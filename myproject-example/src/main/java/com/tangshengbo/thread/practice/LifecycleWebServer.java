package com.tangshengbo.thread.practice;

import jodd.util.ThreadUtil;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by TangShengBo on 2017/12/4.
 */
public class LifecycleWebServer {

    private final ExecutorService exec = Executors.newCachedThreadPool();

    public void start(ServerSocket socket) throws IOException {
        System.out.println("服务器启动...........");
        while (!Thread.currentThread().isInterrupted()) {
            try {
                final Socket conn = socket.accept();
                Thread.sleep(1000);
                System.out.println("服务器运行..........");
//                exec.execute(() -> handleRequest(conn));
//                ThreadUtil.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(ExceptionUtils.getStackTrace(e));
                Thread.currentThread().interrupt();
            }
        }
    }

    private void stop() {
        System.out.println("服务器关闭...........");
        Thread.currentThread().interrupt();
//        exec.shutdown();
    }

    private void handleRequest(Socket connection) {
        Request req = readRequest(connection);
        dispatchRequest(null);
    }

    private interface Request {
    }

    private Request readRequest(Socket s) {
        return null;
    }

    private void dispatchRequest(Request r) {
    }

    private boolean isShutdownRequest(Request r) {
        return false;
    }

    public static void main(String[] args) {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(8086);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final ServerSocket finalSocket = socket;
        Runnable r = () -> {
            try {
                LifecycleWebServer webServer = new LifecycleWebServer();
                webServer.start(finalSocket);
            } catch (IOException e) {
                System.out.println(ExceptionUtils.getStackTrace(e));
            }
        };
        Thread thread = new Thread(r, "webServer");
        thread.start();
        ThreadUtil.sleep(2000);
        try {
            assert finalSocket != null;
            finalSocket.close();
        } catch (IOException e) {
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
//        ThreadUtil.sleep(1000);
//        System.out.println("23423423423");
//        thread.interrupt();
    }
}
