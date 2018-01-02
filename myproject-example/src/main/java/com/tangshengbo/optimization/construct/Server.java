package com.tangshengbo.optimization.construct;

/**
 * Created by Tangshengbo on 2018/1/2.
 */
public abstract class Server {

    public static final int DEFAULT_PORT = 40000;

    public Server() {
        int port = getPort();
        System.out.println("端口号:" + port);
    }

    public abstract int getPort();
}
