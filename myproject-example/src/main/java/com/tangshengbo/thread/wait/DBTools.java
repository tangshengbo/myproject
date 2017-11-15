package com.tangshengbo.thread.wait;

import jodd.util.ThreadUtil;

import java.io.Serializable;

/**
 * Created by TangShengBo on 2017-11-07.
 */
public class DBTools implements Serializable {

    private volatile boolean preIsA = false;

    public synchronized void backupA() {
        while (preIsA) {
            ThreadUtil.wait(this);
            System.out.println(Thread.currentThread().getName() + " wait!");
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "  ★★★★★ ");
        }
        preIsA = true;
        ThreadUtil.notifyAll(this);
    }

    public synchronized void backupB() {
        while (!preIsA) {
            ThreadUtil.wait(this);
            System.out.println(Thread.currentThread().getName() + " wait!");
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "  ☆☆☆☆☆ ");
        }
        preIsA = false;
        ThreadUtil.notifyAll(this);
    }
}
