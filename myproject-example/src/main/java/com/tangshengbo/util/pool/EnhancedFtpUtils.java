package com.tangshengbo.util.pool;


import com.tangshengbo.util.FtpUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by TangShengBo on 2018/1/15.
 */
@Component
public class EnhancedFtpUtils extends FtpUtils {

    private static final Logger logger = LoggerFactory.getLogger(EnhancedFtpUtils.class);

    @Autowired
    private FTPClientPool ftpClientPool;

    /**
     * 从本地线程变量获取实例，没有就创建一个
     * @return
     */
    @Override
    protected FTPClient getFTPClient() {
        FTPClient ftpClient = ftpClientThreadLocal.get();
        if (Objects.nonNull(ftpClient) && ftpClient.isConnected()) {
            return ftpClient;
        }
        FTPClient newFtpClient = null;
        try {
            newFtpClient = ftpClientPool.borrowObject();
            ftpClientThreadLocal.set(newFtpClient);
        } catch (Exception e) {
            logger.error("获取连接异常:{}", e);
        }
        logger.info("打开连接成功! {}", Thread.currentThread().getName());
        return newFtpClient;
    }

    @Override
    public void closeConnection() {
        try {
            ftpClientPool.returnObject(getFTPClient());
            ftpClientThreadLocal.remove();
            logger.info("关闭连接成功! {}", Thread.currentThread().getName());
        } catch (Exception e) {
            logger.error("关闭连接异常:{}", e);
        }
    }
}
