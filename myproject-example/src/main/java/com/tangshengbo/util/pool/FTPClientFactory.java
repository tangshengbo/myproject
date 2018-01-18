package com.tangshengbo.util.pool;

import com.tangshengbo.collection.FTPClientException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool.PoolableObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by Tangshengbo on 2018/1/12.
 */

public class FTPClientFactory implements PoolableObjectFactory<FTPClient> {

    private static final Logger logger = LoggerFactory.getLogger(FTPClientFactory.class);

    private FTPClientConfigure config;

    public FTPClientFactory(FTPClientConfigure config) {
        this.config = config;
    }

    /**
     * 创建FTPClient实例
     *
     * @return
     * @throws Exception
     */
    public FTPClient makeObject() throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(config.getClientTimeout());
        try {
            ftpClient.connect(config.getHost(), config.getPort());
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                logger.warn("FTPServer refused connection");
                return null;
            }
            boolean result = ftpClient.login(config.getUsername(), config.getPassword());
            if (!result) {
                throw new FTPClientException("ftpClient登陆失败! userName:" + config.getUsername() + " ; password:" + config.getPassword());
            }
            ftpClient.setFileType(config.getTransferFileType());
            ftpClient.setBufferSize(config.getBufferSize());
            ftpClient.setControlEncoding(config.getEncoding());
            if (config.isPassiveMode()) {
                ftpClient.enterLocalPassiveMode();
            }
        } catch (IOException e) {
            throw new FTPClientException(e);
        }
        return ftpClient;
    }

    /**
     * 销毁FTPClient实例
     *
     * @return
     * @throws Exception
     */
    public void destroyObject(FTPClient ftpClient) throws Exception {
        try {
            if (ftpClient != null && ftpClient.isConnected()) {
                ftpClient.logout();
            }
        } catch (IOException e) {
            logger.warn("FTPClient 连接关闭异常:{}", e);
        } finally {
            // 注意,一定要在finally代码中断开连接，否则会导致占用ftp连接情况
            try {
                if (ftpClient != null) {
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                logger.warn("FTPClient 连接关闭异常:{}", e);
            }
        }
    }

    /**
     * 验证FTPClient是否有效
     *
     * @param ftpClient
     * @return
     */
    public boolean validateObject(FTPClient ftpClient) {
        try {
            return ftpClient.sendNoOp();
        } catch (IOException e) {
            return false;
        }
    }

    public void activateObject(FTPClient ftpClient) throws Exception {
    }

    public void passivateObject(FTPClient ftpClient) throws Exception {

    }
}
