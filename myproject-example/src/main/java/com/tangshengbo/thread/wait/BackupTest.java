package com.tangshengbo.thread.wait;

import jodd.util.ThreadUtil;

/**
 * Created by TangShengBo on 2017-11-07.
 */
public class BackupTest {

    public static void main(String[] args) {
        DBTools dbTools = new DBTools();
        BackupA backupA = new BackupA(dbTools);
        BackupB backupB = new BackupB(dbTools);
        for (int i = 0; i < 20; i++) {
            ThreadUtil.sleep(500);
            new Thread(backupA).start();
            new Thread(backupB).start();
        }
    }
}
