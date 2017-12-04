package com.tangshengbo.thread.practice;

import jodd.util.ThreadUtil;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by TangShengBo on 2017/12/4.
 */
public class LifecycleWebServer {

    private final ExecutorService exec = Executors.newCachedThreadPool();

    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        System.out.println("服务器启动...........");
        while (!exec.isShutdown()) {
            try {
                final Socket conn = socket.accept();
                exec.execute(() -> handleRequest(conn));
            } catch (RejectedExecutionException e) {
                if (!exec.isShutdown())
                    log("task submission rejected", e);
            }
        }
    }

    private void stop() {
        exec.shutdown();
    }

    private void log(String msg, Exception e) {
        Logger.getAnonymousLogger().log(Level.WARNING, msg, e);
    }

    private void handleRequest(Socket connection) {
        Request req = readRequest(connection);
        dispatchRequest(req);
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
        LifecycleWebServer webServer = new LifecycleWebServer();
        try {
            Runnable r = () -> {
                ThreadUtil.sleep(3000);
                webServer.stop();
            };
            new Thread(r, "shutdown").start();
            webServer.start();
        } catch (IOException e) {
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
    }
}
