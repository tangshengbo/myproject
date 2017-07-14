package com.tangshengbo.io.nio;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * Created by Tang on 2017/7/14.
 */
public class NIOClient {

    //通道管理器
    private Selector selector;

    /**
     * 获得一个Socket通道，并对该通道做一些初始化的工作
     *
     * @param ip   连接的服务器的ip
     * @param port 连接的服务器的端口号
     * @throws IOException
     */
    public void initClient(String ip, int port) throws IOException {
        // 获得一个Socket通道
        SocketChannel channel = SocketChannel.open();
        // 设置通道为非阻塞
        channel.configureBlocking(false);
        // 获得一个通道管理器
        this.selector = Selector.open();

        // 客户端连接服务器,其实方法执行并没有实现连接，需要在listen（）方法中调
        //用channel.finishConnect();才能完成连接
        channel.connect(new InetSocketAddress(ip, port));
        //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件。
        channel.register(selector, SelectionKey.OP_CONNECT);
    }

    /**
     * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
     *
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public void listen() throws IOException {
        // 轮询访问selector
        while (true) {
            selector.select();
            // 获得selector中选中的项的迭代器
            Iterator ite = this.selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = (SelectionKey) ite.next();
                // 删除已选的key,以防重复处理
                ite.remove();
                // 连接事件发生
                if (key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) key
                            .channel();
                    // 如果正在连接，则完成连接
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();

                    }
                    // 设置成非阻塞
                    channel.configureBlocking(false);

                    //在这里可以给服务端发送信息哦
                    channel.write(ByteBuffer.wrap(new String("向服务端发送了一条信息").getBytes("UTF-8")));
                    //在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。
                    channel.register(this.selector, SelectionKey.OP_READ);

                    // 获得了可读的事件
                } else if (key.isReadable()) {
                    read(key);
                }

            }

        }
    }

    /**
     * 处理读取服务端发来的信息 的事件
     *
     * @param key
     * @throws IOException
     */
    public void read(SelectionKey key) throws IOException {
        //和服务端的read方法一样
    }

    public static void main(String[] args) {
//        writeFile();
//        readFile();
//        copyFile();
        transfer();
//        NIOClient client = new NIOClient();
//        try {
//            client.initClient("localhost", 8000);
//            client.listen();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private static void readFile() {
        FileInputStream fis = null;
        FileChannel fc;
        ByteBuffer buf;

        try {
            fis = new FileInputStream("E:/log.txt");
            fc = fis.getChannel();
            buf = ByteBuffer.allocate(1024);
            int bytes = fc.read(buf);
            while (bytes != -1) {
                System.out.println(bytes);
                while (buf.hasRemaining()) {
                    System.out.println(new String(buf.array(), "UTF-8"));
                }
                buf.clear();
                bytes = fc.read(buf);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeFile() {
        RandomAccessFile fos = null;
        FileChannel fc;
        ByteBuffer buf;

        try {
            fos = new RandomAccessFile("E:/log.txt", "rw");
            fc = fos.getChannel();
            buf = ByteBuffer.allocate(1024);
            for (int i = 0; i < 100; i++) {
                buf.put("唐声波".getBytes("UTF-8"));
            }
            buf.flip();
            fc.write(buf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void copyFile() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel fcIn;
        FileChannel fcOut;
        ByteBuffer buf;
        try {
            fis = new FileInputStream("E:/nginx-1.8.133.zip");
            fos = new FileOutputStream("D:/nginx-1.8.133.zip");

            //获取输入输出通道
            fcIn = fis.getChannel();
            fcOut = fos.getChannel();
            buf = ByteBuffer.allocate(10240);
            System.out.println("copyFile start.............");
            while (true) {

                //重设缓冲区，使它可以接受读入的数据
                buf.clear();
                int r = fcIn.read(buf);
                if (r == -1) {
                    break;
                }
                //让缓冲区可以将新读入的数据写入另一个通道
                buf.flip();
                //从输出通道将数据写入缓冲区
                fcOut.write(buf);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                fis.close();
                System.out.println("copyFile end.............");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void transfer() {
        RandomAccessFile fromFile = null;
        RandomAccessFile toFile = null;
        try {
            fromFile = new RandomAccessFile("E:/fromFile.txt", "rw");
            toFile = new RandomAccessFile("E:/toFile.txt", "rw");

            FileChannel toChannel = toFile.getChannel();
            FileChannel fromChannel = fromFile.getChannel();
            long position = 0;
            long count = fromChannel.size();

            fromChannel.transferTo(position, count, toChannel);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                toFile.close();
                fromFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
