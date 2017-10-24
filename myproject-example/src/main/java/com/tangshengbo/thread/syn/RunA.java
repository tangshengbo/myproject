package com.tangshengbo.thread.syn;

/**
 * Created by Tangshengbo on 2017/10/24.
 */
public class RunA implements Runnable {

    private ObjectService objectService;

    public RunA(ObjectService objectService) {
        this.objectService = objectService;
    }

    @Override
    public void run() {
        objectService.serviceMethod();
    }
}
