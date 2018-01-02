package com.tangshengbo.optimization.construct;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * Created by Tangshengbo on 2018/1/2.
 */
public class ServerTest {

    public static void main(String[] args) {
        Server server = new SimpleServer(1000);
        System.out.println(server.getPort());
        System.out.println("==================================");
        SimpleServer simpleServer = new SimpleServer();
        System.out.println(simpleServer.getStudent());
        System.out.println("==================================");
        try {
            SimpleServer cloneServer = (SimpleServer)simpleServer.clone();
            System.out.println(cloneServer.getStudent());
        } catch (CloneNotSupportedException e) {
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
    }
}
