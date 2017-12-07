package com.tangshengbo.thread.practice.interrupt;

import jodd.util.ThreadUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by TangShengBo on 2017/12/7.
 */
public class LogService {

    private final BlockingQueue<String> queue;
    private final PrintWriter writer;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public LogService(Writer writer) {
        this.queue = new LinkedBlockingQueue<>();
        this.writer = new PrintWriter(writer);
    }

    public void start() {
        executorService.execute(new LogTask());
    }

    public void stop() {
        IOUtils.closeQuietly(writer);
        executorService.shutdown();
    }

    public void log(String msg) throws InterruptedException {
        queue.put(msg);
    }

    private class LogTask implements Runnable {

        public void run() {
            String msg;
            try {
                msg = queue.take();
                writer.println(msg);
            } catch (InterruptedException e) {
                System.out.println(ExceptionUtils.getStackTrace(e));
            }
        }
    }

    public static void main(String[] args) {
        LogService service = new LogService(new OutputStreamWriter(System.out));
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
