package com.tangshengbo.design.template.download;

/**
 * Created by TangShengBo on 2017-09-13.
 */
public abstract class CheckFileDownload {

    protected abstract void init();

    protected abstract void readFileData();

    protected abstract void writeDB();

    public final void fileDownload() {
        init();
        readFileData();
        writeDB();
    }
}
