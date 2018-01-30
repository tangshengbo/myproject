package com.tangshengbo.optimization.download;

/**
 * Created by Tangshengbo on 2018/1/30.
 */
public class BfDownloader extends AbstractDownloader {

    @Override
    public void execute() {
        successCount = 1000;
        failCount = 2000;
    }
}
