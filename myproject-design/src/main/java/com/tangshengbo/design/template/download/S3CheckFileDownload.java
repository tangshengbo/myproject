package com.tangshengbo.design.template.download;

/**
 * Created by TangShengBo on 2017-09-13.
 */
public class S3CheckFileDownload extends CheckFileDownload {

    @Override
    protected void init() {
        System.out.println(this.getClass().getName() + "\t初始化............");

    }

    @Override
    protected void readFileData() {
        System.out.println(this.getClass().getName() + "\treadFileData............");
    }

    @Override
    protected void writeDB() {
        System.out.println(this.getClass().getName() + "\twriteDB............");
    }
}
