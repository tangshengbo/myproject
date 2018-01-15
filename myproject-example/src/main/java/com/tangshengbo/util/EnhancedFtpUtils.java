package com.tangshengbo.util;

import org.apache.commons.net.ftp.FTPClient;

/**
 * Created by TangShengBo on 2018/1/15.
 */
public class EnhancedFtpUtils extends FtpUtils {

    @Override
    protected FTPClient getFTPClient() {
        return super.getFTPClient();
    }

    @Override
    public void closeConnection() {
        super.closeConnection();
    }
}
