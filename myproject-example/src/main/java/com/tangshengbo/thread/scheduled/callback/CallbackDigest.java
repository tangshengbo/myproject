package com.tangshengbo.thread.scheduled.callback;

import jodd.util.ThreadUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

/**
 * Created by Tangshengbo on 2018/1/31.
 */
public class CallbackDigest implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(CallbackDigest.class);

    private InstanceCallBack instanceCallBack;

    public CallbackDigest(InstanceCallBack instanceCallBack) {
        this.instanceCallBack = instanceCallBack;
    }

    @Override
    public void run() {
        logger.info("加密开始..........");
        DigestInputStream is;
        try (FileInputStream fis = new FileInputStream(instanceCallBack.getFileName())) {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            is = new DigestInputStream(fis, sha);
            while (is.read() != -1) ;
            ThreadUtil.sleep(3000);
            instanceCallBack.receiveDigest(sha.digest());
        } catch (Exception e) {
            logger.error("加密异常:{}", ExceptionUtils.getStackTrace(e));
        }
        logger.info("加密结束..........");
    }
}