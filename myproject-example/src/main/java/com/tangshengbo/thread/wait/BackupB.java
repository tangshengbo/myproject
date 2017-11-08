package com.tangshengbo.thread.wait;

import jodd.util.ThreadUtil;

/**
 * Created by TangShengBo on 2017-11-07.
 */
public class BackupB implements Runnable {

    private DBTools dbTools;

    public BackupB(DBTools dbTools) {
        this.dbTools = dbTools;
    }

    @Override
    public void run() {
        ThreadUtil.sleep(2000);
        dbTools.backupB();
    }
}
