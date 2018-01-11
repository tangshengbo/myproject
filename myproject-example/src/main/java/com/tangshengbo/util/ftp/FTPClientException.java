package com.tangshengbo.util.ftp;

/**
 * Created by Tangshengbo on 2018/1/11.
 */
public class FTPClientException extends RuntimeException {

    public FTPClientException(String message) {
        super(message);
    }

    public FTPClientException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
