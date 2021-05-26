package com.tangshengbo.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Tangshengbo on 2021/4/19
 */
public class NettyHttpServer {


    public static void main(String[] args) {
        new NettyHttpServer().start();
        testPowerOfTow();
    }

    private static void testPowerOfTow() {
        AtomicInteger idx = new AtomicInteger();
        idx.getAndIncrement();
        int length = 16;
        for (int i = 0; i < 1000; i++) {
            if (isPowerOfTwo(i)) {
                System.out.printf("val:%s (val & -val):%s%n", i, (i & -i));
            }
            int index = idx.getAndIncrement() & length - 1;
            System.err.println(i + "index" + index);;
        }
    }

    // 判断线程任务执行的个数是否为 2 的幂次方。e.g: 2、4、8、16
    private static boolean isPowerOfTwo(int val) {
//        System.out.println((val & -val) );
        return (val & -val) == val;
    }


    public void start() {

        //eventLoop-1-XXX

        //Netty封装了NIO，Reactor模型，Boss，worker
        // Boss线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // Worker线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            //1、创建对象
            // Netty服务
            //ServetBootstrap   ServerSocketChannel
            ServerBootstrap server = new ServerBootstrap();

            //2、配置参数
            // 链路式编程
            server.group(bossGroup, workerGroup)
                    // 主线程处理类,看到这样的写法，底层就是用反射
                    .channel(NioServerSocketChannel.class)
                    // 子线程处理类 , Handler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 客户端初始化处理
                        protected void initChannel(SocketChannel client) throws Exception {
                            // 无锁化串行编程
                            //Netty对HTTP协议的封装，顺序有要求
                            // HttpResponseEncoder 编码器
                            // 责任链模式，双向链表Inbound OutBound
                            client.pipeline().addLast(new HttpResponseEncoder());
                            // HttpRequestDecoder 解码器
                            client.pipeline().addLast(new HttpRequestDecoder());
                            client.pipeline().addLast(new HttpContentCompressor());
                            //http请求报文聚合为完整报文，最大请求报文为10M
                            client.pipeline().addLast(new HttpObjectAggregator(10 * 1024 * 1024));
                            // 业务逻辑处理
                            client.pipeline().addLast(new TomcatHandler());
                        }
                    })
                    // 针对主线程的配置 分配线程最大数量 128
                    .option(ChannelOption.SO_BACKLOG, 128)
//					.childOption(ChannelOption.SINGLE_EVENTEXECUTOR_PER_GROUP, false)
                    // 针对子线程的配置 保持长连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true);

            //3、启动服务器
            int port = 8888;
            ChannelFuture f = server.bind(port).sync();
            System.out.println("Netty Tomcat 已启动，监听的端口是：" + port);
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


}
