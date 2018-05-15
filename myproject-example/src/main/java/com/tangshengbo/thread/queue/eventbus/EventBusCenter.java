package com.tangshengbo.thread.queue.eventbus;

import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by TangShengBo on 2018/5/15.
 */
public class EventBusCenter {

    private static Executor executor = Executors.newFixedThreadPool(1000);

    private static EventBus eventBus = new EventBus(new MySubscriberExceptionHandler());

    private EventBusCenter() {

    }
    public static void register(Object obj) {
        eventBus.register(obj);
    }

    public static void unregister(Object obj) {
        eventBus.unregister(obj);
    }

    public static void post(Object obj) {
        eventBus.post(obj);
    }
}
