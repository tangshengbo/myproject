package com.tangshengbo.thread.wait;

import jodd.util.ThreadUtil;

/**
 * Created by TangShengBo on 2017-11-07.
 */
public class BackupA implements Runnable {

    private DBTools dbTools;

    public BackupA(DBTools dbTools) {
        this.dbTools = dbTools;
    }

    @Override
    public void run() {
        ThreadUtil.sleep(2000);
        dbTools.backupA();
    }
}
