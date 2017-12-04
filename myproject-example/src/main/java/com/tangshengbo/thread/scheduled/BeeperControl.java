package com.tangshengbo.thread.scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tang on 2017/6/29.
 */
public class BeeperControl {

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(10);

    public void beepForAnHour() {

        final Runnable beeper = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("beep............." + Thread.currentThread().getName());

        };

        final ScheduledFuture<?> beeperHandle =
                scheduler.scheduleAtFixedRate(beeper, 2, 2, TimeUnit.SECONDS);

        scheduler.schedule((Runnable) () -> {
            beeperHandle.cancel(true);
            System.out.println("stop schedule.......");
            System.exit(0);
        }, 10, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        BeeperControl beeperControl = new BeeperControl();
        beeperControl.beepForAnHour();
    }
}

