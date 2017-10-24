package com.tangshengbo.thread.syn;

/**
 * Created by Tangshengbo on 2017/10/24.
 */
public class RunC implements Runnable {

    private ObjectService objectService;

    public RunC(ObjectService objectService) {
        this.objectService = objectService;
    }

    @Override
    public void run() {
//        objectService.serviceMethod();
//        objectService.setUsernamePassword("a", "aa");
//        objectService.serviceSynchronized();
        objectService.addServiceMethod("C");
    }

}
