package com.tangshengbo.optimization.download;

/**
 * Created by Tangshengbo on 2018/1/30.
 */
public class FyDownloader extends AbstractDownloader {

    @Override
    public void execute() {
        successCount = 100;
        failCount = 200;
    }
}
