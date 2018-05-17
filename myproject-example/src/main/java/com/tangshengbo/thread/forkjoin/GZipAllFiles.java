package com.tangshengbo.thread.forkjoin;

import com.tangshengbo.datetime.DateUtil;
import org.apache.commons.lang.time.FastDateFormat;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GZipAllFiles {

    private static final Logger logger = LoggerFactory.getLogger(GZipAllFiles.class);

    private final static int THREAD_COUNT = 4;

    @Test
    public void testGZipFile() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
        File rootFile = new File("E:/cas");
        logger.info("{}", rootFile.toPath().getRoot());
        System.out.println();
        String lastModifiedDate = FastDateFormat.getInstance(DateUtil.DEFAULT_DATE_FORMAT).format(rootFile.lastModified());
        System.out.println(lastModifiedDate);
        if (rootFile.exists()) {
            Runnable task = new GZipRunnable(rootFile);
            pool.submit(task);
        }
        pool.shutdown();
    }
}