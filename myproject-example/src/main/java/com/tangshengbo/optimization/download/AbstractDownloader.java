package com.tangshengbo.optimization.download;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Tangshengbo on 2018/1/30.
 */
public abstract class AbstractDownloader {

    private static final Logger logger = LoggerFactory.getLogger(AbstractDownloader.class);

    protected List<String> successFileNames;

    protected int failCount;

    protected int successCount;

    public void download() {
        init();
        execute();
        close();
    }

    public abstract void execute();

    protected void init() {
        logger.info("调用：init.............");
        logger.info("{} {}", failCount, successCount);
        failCount = 0;
        successCount = 0;
        successFileNames = Lists.newArrayList();
    }


    protected void close() {
        logger.info("调用：close.............");
        logger.info("{} {}", failCount, successCount);
        successFileNames = null;
    }

}
