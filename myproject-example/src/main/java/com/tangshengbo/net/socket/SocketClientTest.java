package com.tangshengbo.net.socket;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by TangShengBo on 2018/4/15.
 */
public class SocketClientTest {

    private static final Logger logger = LoggerFactory.getLogger(SocketClientTest.class);

    @Test
    public void testSocket() throws Exception {
        for (int i = 0; i < 10000; i++) {
            InetAddress inetAddress = InetAddress.getByName("localhost");
            logger.info("{}", inetAddress);
//        Socket socket = new Socket("47.52.146.64", 8080);
            Socket socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(inetAddress, 8086);
            socket.connect(socketAddress);
            socket.setSoTimeout(15000);
            OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
            out.write("啦啦啦放松放松");
            out.flush();
            socket.shutdownOutput();
            logger.info("LocalSocketAddress :{}", socket.getLocalSocketAddress());
            logger.info("RemoteSocketAddress:{}", socket.getRemoteSocketAddress());
            logger.info("{}", IOUtils.toString(socket.getInputStream(), "UTF-8"));
            socket.close();
        }
    }
}
