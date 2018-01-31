package com.tangshengbo.thread.scheduled.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;

/**
 * Created by Tangshengbo on 2018/1/31.
 */
public class InstanceCallBack {

    private static final Logger logger = LoggerFactory.getLogger(InstanceCallBack.class);

    private String fileName;
    private byte[] digest;


    public String getFileName() {
        return fileName;
    }

    public InstanceCallBack(String fileName) {
        this.fileName = fileName;
    }

    public void calculateDigest() {
        Thread thread = new Thread(new CallbackDigest(this));
        thread.start();
    }

    public void receiveDigest(byte[] digest) {
        this.digest = digest;
        logger.info("receiveDigest:{}", this);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(fileName).append(":")
                .append(DatatypeConverter.printBase64Binary(digest))
                .append(DatatypeConverter.printHexBinary(digest));
        return sb.toString();
    }
}
