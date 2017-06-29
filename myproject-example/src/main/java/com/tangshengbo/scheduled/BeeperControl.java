package com.tangshengbo.scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tang on 2017/6/29.
 */
public class BeeperControl {
    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    public void beepForAnHour() {

        final Runnable beeper = new Runnable() {
            public void run() {
                System.out.println("beep");
            }
        };

        final ScheduledFuture<?> beeperHandle =
                scheduler.scheduleAtFixedRate(beeper, 2, 2, TimeUnit.SECONDS);

        scheduler.schedule(new Runnable() {
            public void run() {
                beeperHandle.cancel(true);
                System.out.println("stop schedule.......");
                System.exit(0);
            }
        }, 10, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        BeeperControl beeperControl = new BeeperControl();
        beeperControl.beepForAnHour();
    }
}

