package com.tangshengbo.io.netty;

import org.jboss.netty.channel.*;

/**
 * Created by Tang on 2017/7/17.
 */
public class HelloServerHandler extends SimpleChannelHandler {

    /**
     * 当有客户端绑定到服务端的时候触发，打印"Hello world, I'm server."
     *
     * @alia OneCoder
     * @author lihzh
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        System.out.println("有一个客户端注册上来了。。。");
        System.out.println("Client:" + e.getChannel().getRemoteAddress());
        System.out.println("Server:" + e.getChannel().getLocalAddress());
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        if (e.getMessage() instanceof Command) {
            Command result = (Command) e.getMessage();
            System.out.println("Client发来:" + result.toString());
            e.getChannel().write("Server已收到刚发送的:" + result.toString());
        }
    }
}
