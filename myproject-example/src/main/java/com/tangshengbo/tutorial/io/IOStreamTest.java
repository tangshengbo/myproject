package com.tangshengbo.tutorial.io;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;

/**
 * Created by TangShengBo on 2017-06-25.
 */
public class IOStreamTest {

    private static final Logger logger = LoggerFactory.getLogger(IOStreamTest.class);

    public static void main(String[] args) {

        copyFile("E:\\New folder\\KTH.mp4", "D:\\KTH.mp4");
//        testByteArrayStream();
    }

    public static void copyFile(String srcFileName, String destFileName) {
        try {
            InputStream is = new FileInputStream(srcFileName);
            OutputStream os = new FileOutputStream(destFileName);
            int byteNum;
            byte[] buf = new byte[408800];
            long startTime = System.currentTimeMillis(); // 获取开始时间
            while ((byteNum = is.read(buf)) != -1) {
                System.out.println(byteNum);
                os.write(buf);
            }
            //将缓冲去的数据全部写到目的地
            os.flush();
            os.close();
            is.close();
            delFile(srcFileName);
            long endTime = System.currentTimeMillis(); // 获取结束时间
            System.out.println("程序运行时间： " + (endTime - startTime) / 1000 + "s");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testByteArrayStream() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        try {
            dos.writeDouble(Math.random());
            dos.writeBoolean(true);
            dos.writeUTF("唐声波");
            ByteArrayInputStream bais =
                    new ByteArrayInputStream(baos.toByteArray());
            System.out.println(bais.available());
            DataInputStream dis = new DataInputStream(bais);
            System.out.println(dis.readDouble());
            System.out.println(dis.readBoolean());
            System.out.println(dis.readUTF());
            dos.close();
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDigest() throws Exception {
        FileInputStream fis = new FileInputStream("E:/http.java");
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        DigestInputStream is = new DigestInputStream(fis, digest);
        while (is.read() != -1);
        is.close();
        byte[] bytes = digest.digest();
        logger.info("Base64:{}", DatatypeConverter.printBase64Binary(bytes));
        logger.info("Base64:{}", DatatypeConverter.printHexBinary(bytes));

    }

    public static void delFile(String filePathAndName) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            java.io.File myDelFile = new java.io.File(filePath);
            myDelFile.delete();
        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();
        }
    }
}
