package com.tangshengbo.design.template.download;

/**
 * Created by TangShengBo on 2017-09-13.
 */
public class CheckFileDownloadTest {

    public static void main(String[] args) {
//        CheckFileDownload checkFileDownload = new FTPCheckFileDownload();
//        checkFileDownload.fileDownload();
//
//        checkFileDownload = new S3CheckFileDownload();
//        checkFileDownload.fileDownload();

        new FTPCheckFileDownload().fileDownload();
    }
}
