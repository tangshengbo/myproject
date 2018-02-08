package com.tangshengbo.thread.forkjoin;

import com.tangshengbo.datetime.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GZipAllFiles {

    public final static int THREAD_COUNT = 4;

    @Test
    public void testGZipFile() {
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
        File rootFile = new File("E:/cas");
        String lastModifiedDate = FastDateFormat.getInstance(DateUtils.DEFAULT_DATE_FORMAT).format(rootFile.lastModified());
        System.out.println(lastModifiedDate);
        if (rootFile.exists()) {
            Runnable task = new GZipRunnable(rootFile);
            pool.submit(task);
        }
        pool.shutdown();
    }
}