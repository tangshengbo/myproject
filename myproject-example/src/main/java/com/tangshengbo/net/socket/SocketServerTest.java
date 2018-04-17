package com.tangshengbo.net.socket;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by TangShengBo on 2018/4/15.
 */
public class SocketServerTest {

    private static final Logger logger = LoggerFactory.getLogger(SocketClientTest.class);

    @Test
    public void testServerSocket() {

        ExecutorService service = Executors.newFixedThreadPool(100);

        try (ServerSocket serverSocket = new ServerSocket(8086)) {
            logger.info("服务器已启动..................");
            while (true) {
                Socket socket = serverSocket.accept();
                service.submit(new DayTimeTask(socket));
            }
        } catch (IOException e) {
            logger.error("{}", ExceptionUtils.getStackTrace(e));
        }
    }

    @Test
    public void testServerPort() {
        for (int i = 1; i < 65535; i++) {
            try {
                ServerSocket serverSocket = new ServerSocket(i);
                logger.info("{}", serverSocket.getReuseAddress());
            } catch (IOException e) {
                logger.info("这个端口不可用 {}", i);
            }
        }
    }

    private static class DayTimeTask implements Callable<Void> {

        private Socket socket;

        public DayTimeTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public Void call() throws Exception {
            InputStreamReader dataInputStream = new InputStreamReader(socket.getInputStream());
            logger.info("{}", IOUtils.toString(dataInputStream));
            OutputStreamWriter dataOutputStream = new OutputStreamWriter(socket.getOutputStream());
            dataOutputStream.write(FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date()));
            dataOutputStream.flush();
            socket.close();
            return null;
        }
    }
}
