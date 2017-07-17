package com.tangshengbo.io.netty;

import org.jboss.netty.channel.*;

/**
 * Created by Tang on 2017/7/17.
 */
public class HelloClientHandler extends SimpleChannelHandler {

    /**
     * 当绑定到服务端的时候触发，打印"Hello world, I'm client."
     *
     * @alia OneCoder
     * @author lihzh
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        this.sendObject(e.getChannel());
        System.out.println("Hello world, I'm client.");
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        if (e.getMessage() instanceof String) {
            String result = (String) e.getMessage();
            System.out.println("Server端发来:\t" + result);
        }
    }

    private void sendObject(Channel channel) {
        Command command = new Command("唐声波");
        channel.write(command);
    }
}
