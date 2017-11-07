package com.tangshengbo.thread.wait;

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
        dbTools.backupB();
    }
}
