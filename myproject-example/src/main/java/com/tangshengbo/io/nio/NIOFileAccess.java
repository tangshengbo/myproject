package com.tangshengbo.io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by TangShengBo on 2017-07-15.
 */
public class NIOFileAccess {

    public static void main(String[] args) {
        copyFile("D:\\KTH.mp4", "E:\\New folder\\KTH.mp4");
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

    private static void copyFile(String srcFileName, String destFileName) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel fcIn;
        FileChannel fcOut;
        ByteBuffer buf;
        try {
            fis = new FileInputStream(srcFileName);
            fos = new FileOutputStream(destFileName);

            //获取输入输出通道
            fcIn = fis.getChannel();
            fcOut = fos.getChannel();
            buf = ByteBuffer.allocate(408800);
            System.out.println("copyFile start.............");
            long startTime = System.currentTimeMillis(); // 获取开始时间
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
            long endTime = System.currentTimeMillis(); // 获取结束时间
            System.out.println("程序运行时间： " + (endTime - startTime) / 1000 + "s");
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
