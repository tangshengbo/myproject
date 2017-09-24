package com.tangshengbo.design.decorate.shape;

import java.io.*;

/**
 * Created by TangShengBo on 2017-09-24.
 */
public class DecoratorPatternTest {

    public static void main(String[] args) throws IOException {
        Shape shape = new Rectangle();
        Shape redShapeDecorator = new RedShapeDecorator(shape);
        Shape backgroundShapeDecorate = new BackgroundShapeDecorate(redShapeDecorator);
        backgroundShapeDecorate.draw();

        // 流式读取文件
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream("test.txt")
                    )
            );
            //读取文件内容
            byte[] bs = new byte[dis.available()];
            dis.read(bs);
            String content = new String(bs);
            System.out.println(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dis.close();
        }
    }
}
