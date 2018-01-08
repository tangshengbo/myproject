package com.tangshengbo.util;

import jodd.util.StringPool;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Objects;

/**
 * Created by Tangshengbo on 2018/1/5.
 */
public class FtpUtils {

    private static final Logger log = LoggerFactory.getLogger(FtpUtils.class);

    private String host;
    private String username;
    private String password;
    private int port;
    private FTPClient ftp;

    public FtpUtils(String host, String username, String password, int port) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    /**
     * 打开FTP连接
     */
    private boolean openConnection() {
        ftp = new FTPClient();
        try {
            //连接登录
            ftp.connect(this.host, this.port);
            ftp.login(this.username, this.password);
            //默认为二进制传输
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            //设置被动模式
            ftp.enterLocalPassiveMode();
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                throw new RuntimeException("连接FTP服务器失败，响应码：" + reply);
            }
        } catch (IOException e) {
            log.error("FTP 打开连接异常 {}", e);
            throw new RuntimeException(e);
        }
        return true;
    }


    /**
     * 上传文件 默认使用(UTF-8)
     *
     * @param remotePath
     * @param localPath
     * @return
     */
    public String uploadFile(String remotePath, String localPath) {
        return this.uploadFile(remotePath, localPath, StringPool.UTF_8);
    }


    /**
     * 上传文件
     *
     * @param remotePath
     * @param localPath
     * @param encoding
     * @return
     */
    public String uploadFile(String remotePath, String localPath, String encoding) {
        boolean isOpen = this.openConnection();
        String remote = null;
        if (isOpen) {
            File file = FileUtils.getFile(localPath);
            try (BufferedInputStream bis = IOUtils.buffer(FileUtils.openInputStream(file))) {
                remote = remotePath + file.getName();
                log.info("打开连接成功，开始上传! 文件名:{}", remote);
                //设置上传目录
                ftp.changeWorkingDirectory(remotePath);
                ftp.setControlEncoding(encoding);
                //上传文件
                ftp.storeFile(remotePath, bis);
                log.info("上传完成!");
            } catch (IOException e) {
                log.error("FTP文件上传异常:{}", e);
                throw new RuntimeException(e);
            }
        }
        return remote;
    }

    /**
     * 下载文件 返回InputStream
     *
     * @param remotePath
     * @param fileName
     * @return
     */
    public InputStream downloadFile(String remotePath, String fileName) {
        boolean isOpen = this.openConnection();
        InputStream is = null;
        if (isOpen) {
            String remote = remotePath + fileName;
            log.info("打开连接成功，开始下载! 文件名:{}", remote);
            try {
                is = ftp.retrieveFileStream(remote);
                if (Objects.isNull(is)) {
                    throw new RuntimeException("文件不存在:" + remote);
                }
            } catch (IOException e) {
                log.error("FTP 文件下载异常:{}", e);
                throw new RuntimeException(e);
            }
        }
        return is;
    }

    /**
     * 下载文件 到指定目录
     *
     * @param remotePath
     * @param fileName
     * @param localPath
     * @return
     */
    public void downloadFile(String remotePath, String fileName, String localPath) {
        try (InputStream is = this.downloadFile(remotePath, fileName);
             OutputStream os = IOUtils.buffer(new FileOutputStream(localPath))) {
            IOUtils.copy(is, os);
        } catch (IOException e) {
            log.error("FTP 文件下载异常:{}", e);
        }
    }

    /**
     * 关闭连接
     */
    public void closeConnection() {
        if (Objects.nonNull(ftp)) {
            try {
                ftp.disconnect();
                log.info("关闭FTP连接!");
            } catch (IOException e) {
                log.error("关闭FTP连接出现异常{}", e);
            }
        }
    }

    public static void main(String[] args) {
        FtpUtils ftpUtils = new FtpUtils("hz-sftp1.lianlianpay.com", "sh-houb", "62G7TujXH", 2122);
//        System.out.println(ftpUtils.openConnection());
        try {
//            InputStream is = ftpUtils.downloadFile("/account/20180105/", "0002900F0306973_20170531.txt");
//            FileUtils.copyInputStreamToFile(is, new File("E://tang.txt"));
            ftpUtils.downloadFile("/sh-houb/201507091000405502/", "YHZZW_201507091000405502_201712.csv", "E://tang.txt");
            ftpUtils.uploadFile("/account/20170531/", "E://ang.txt");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ftpUtils.closeConnection();
        }
    }
}
