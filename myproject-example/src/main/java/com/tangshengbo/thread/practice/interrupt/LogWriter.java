package com.tangshengbo.thread.practice.interrupt;

import jodd.util.ThreadUtil;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by TangShengBo on 2017/12/7.
 */
public class LogWriter {

    private final BlockingQueue<String> queue;
    private final LoggerThread loggerThread;
    private final PrintWriter writer;
    private boolean isShutdown;

    /**
     * 消息队列的计数，保证消费完
     */
    private int reservations;

    public LogWriter(Writer writer) {
        this.queue = new LinkedBlockingQueue<>();
        this.loggerThread = new LoggerThread();
        this.writer = new PrintWriter(writer);
    }

    public void start() {
        loggerThread.start();
    }

    public void stop() {
        synchronized (this) {
            isShutdown = true;
        }
        loggerThread.interrupt();
    }

    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if (isShutdown)
                throw new IllegalStateException("Cannot log, log service shutdown!");
            ++reservations;
        }
        queue.put(msg);
    }

    private class LoggerThread extends Thread {
        public void run() {
            try {
                while (true) {
                    try {
                        synchronized (LogWriter.this) {
                            if (isShutdown && reservations == 0)
                                break;
                        }
                        String msg = queue.take();
                        synchronized (LogWriter.this) {
                            --reservations;
                        }
                        writer.println(msg);
                    } catch (InterruptedException e) {
                        System.out.println(ExceptionUtils.getStackTrace(e));
                        /**
                         * retry
                         * */
                    }
                }
            } finally {
                writer.close();
            }
        }
    }

    public static void main(String[] args) {
        LogWriter service = new LogWriter(new OutputStreamWriter(System.out));
        try {
            service.log("糖果个");
        } catch (InterruptedException e) {
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
        ThreadUtil.sleep(2000);
        service.start();
        ThreadUtil.sleep(2000);
        service.stop();
    }
}
