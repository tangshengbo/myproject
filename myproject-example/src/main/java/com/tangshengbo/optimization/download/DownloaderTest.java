package com.tangshengbo.optimization.download;

import jodd.util.ThreadUtil;
import org.junit.Test;

/**
 * Created by Tangshengbo on 2018/1/30.
 */
public class DownloaderTest {

    @Test
    public void testDownload() {
        AbstractDownloader bf = new BfDownloader();
        AbstractDownloader fy = new FyDownloader();
        Runnable r = bf::download;
        Runnable r2 = fy::download;
        new Thread(r, "BF-Thread").start();
        new Thread(r2, "FY-Thread").start();

        ThreadUtil.sleep(10000000);
    }

}
