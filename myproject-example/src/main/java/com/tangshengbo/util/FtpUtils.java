package com.tangshengbo.util;

import jodd.util.StringPool;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
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

    protected ThreadLocal<FTPClient> ftpClientThreadLocal = new ThreadLocal<>();
    private String host;
    private String username;
    private String password;
    private int port;

    public FtpUtils(String host, String username, String password, int port) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    public FtpUtils() {
    }

    /**
     * 从本地线程变量获取实例，没有就创建一个
     *
     * @return
     */
    protected FTPClient getFTPClient() {
        FTPClient ftpClient = ftpClientThreadLocal.get();
        if (Objects.nonNull(ftpClient) && ftpClient.isConnected()) {
            return ftpClient;
        }
        //构造一个FtpClient实例
        FTPClient newFtpClient = new FTPClient();
        try {
            //连接登录
            newFtpClient.connect(this.host, this.port);
            newFtpClient.login(this.username, this.password);
            //默认为二进制传输
            newFtpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //设置被动模式
            newFtpClient.enterLocalPassiveMode();
            int reply = newFtpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                newFtpClient.disconnect();
                throw new RuntimeException("连接FTP服务器失败，响应码：" + reply);
            }
            ftpClientThreadLocal.set(newFtpClient);
        } catch (IOException e) {
            log.error("FTP 打开连接异常 {}", e);
            throw new RuntimeException(e);
        }
        log.info("打开连接成功! {}", Thread.currentThread().getName());
        return newFtpClient;
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
        final FTPClient ftpClient = this.getFTPClient();
        String remote = null;
        if (Objects.nonNull(ftpClient)) {
            File file = FileUtils.getFile(localPath);
            try (BufferedInputStream bis = IOUtils.buffer(FileUtils.openInputStream(file))) {
                remote = remotePath + file.getName();
                log.info("开始上传! 文件名:{}", remote);
                //设置上传目录
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setControlEncoding(encoding);
                //上传文件
                ftpClient.storeFile(remotePath, bis);
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
        final FTPClient ftpClient = this.getFTPClient();
        InputStream is = null;
        if (Objects.nonNull(ftpClient)) {
            String remote = remotePath + fileName;
            log.info("开始下载! 文件名:{}", remote);
            try {
                if (!existsFile(remote)) {
                    throw new FileNotFoundException(remote);
                }
                is = ftpClient.retrieveFileStream(remote);
            } catch (IOException e) {
                log.error("FTP 文件下载异常:{}", e);
                throw new RuntimeException(e);
            }
        }
        return is;
    }

    /**
     * 文判断文件是否存在
     *
     * @param remote
     * @throws IOException
     */
    private boolean existsFile(String remote) throws IOException {
        FTPFile[] ftpFiles = getFTPClient().listFiles(remote);
        return ftpFiles.length == 1;
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
        final FTPClient ftpClient = getFTPClient();
        if (Objects.nonNull(ftpClient) && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                log.info("关闭FTP连接! {}", Thread.currentThread().getName());
            } catch (IOException e) {
                log.error("关闭FTP连接出现异常{}", e);
            } finally {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    log.error("关闭FTP连接出现异常{}", e);
                }
                ftpClientThreadLocal.remove();
            }
        }
    }

    /**
     * 在下载多个文件时,必须执行 否则  retrieveFileStream 返回NULL
     */
    public void completePendingCommand() {
        try {
            getFTPClient().completePendingCommand();
        } catch (IOException e) {
            log.error("等待服务器返回完成命令出现异常:{}", e);
        }
    }
}
