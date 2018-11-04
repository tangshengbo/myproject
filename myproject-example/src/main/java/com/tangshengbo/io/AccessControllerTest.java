package com.tangshengbo.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Created by Tangshengbo on 2018/10/30
 */
public class AccessControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(AccessControllerTest.class);

    // 工程 A 执行文件的路径
    private final static String FOLDER_PATH = "D:/check/";

    public static void main(String[] args) throws Exception {
        // 打开系统安全权限检查开关
        System.out.println(System.getProperty("java.security.policy"));
        System.out.println(System.getSecurityManager());
        System.setSecurityManager(new SecurityManager());
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
         AccessController.doPrivileged(new PrivilegedAction<File>() {
            @Override
            public File run() {
                File fs = new File(FOLDER_PATH + "/" + "xx.txt");
                try {
                    fs.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return fs;
            }
        });


//        doPrivilegedAction("xx.txt");
//        FileUtil.delete();
    }


    private static File makeFile(String fileName) {
            // 尝试在工程 A 执行文件的路径中创建一个新文件
            File fs = new File(FOLDER_PATH + "/" + fileName);
        try {
            boolean newFile = fs.createNewFile();
            logger.info("createNewFile-> {}", newFile);
            return fs;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    private static File doPrivilegedAction(final String fileName) {
//        // 用特权访问方式创建文件
//
//    }

}
