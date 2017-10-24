package com.tangshengbo.thread.syn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tangshengbo on 2017/10/24.
 */
public class SynchronizedTest {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        ObjectService objectService = new ObjectService(strings);
        ObjectService objectService2 = new ObjectService();

        RunA runA = new RunA(objectService);
        Thread threadA = new Thread(runA, "threadA");
        threadA.start();

        RunC runC = new RunC(objectService);
        Thread threadC = new Thread(runC, "threadC");
        threadC.start();

        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ListSize:" + strings.size());
    }
}
