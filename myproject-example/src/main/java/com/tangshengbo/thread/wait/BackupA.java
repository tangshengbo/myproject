package com.tangshengbo.thread.wait;

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
        dbTools.backupA();
    }
}
