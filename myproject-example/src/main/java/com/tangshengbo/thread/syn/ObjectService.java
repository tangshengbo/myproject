package com.tangshengbo.thread.syn;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tangshengbo on 2017/10/24.
 */
public class ObjectService {

    private String userName;

    private String password;

    private String anyString = new String();

    private List<String> strings;

    public ObjectService() {
    }

    public ObjectService(List<String> strings) {
        this.strings = strings;
    }

    public void serviceMethod() {
        try {
            System.out.println(Thread.currentThread().getName() + " begin time：" + System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + " end time:" + System.currentTimeMillis());
            synchronized (ObjectService.class) {
                System.out.println(Thread.currentThread().getName() + " synchronized 当前线程：" + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void serviceSynchronized() {
        try {
            for (int i = 0; i < 5; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("asynchronous ThreadName:" + Thread.currentThread().getName() + ":i=" + i);
            }
//            synchronized (ObjectService.class) {
            for (int i = 0; i < 5; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("synchronized  ThreadName:" + Thread.currentThread().getName() + ":i=" + i);
            }
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setUsernamePassword(String userName, String password) {
        synchronized (this) {
            System.out.println("setUsernamePassword" + Thread.currentThread().getName() + " begin time：" + System.currentTimeMillis());
            this.userName = userName;
            this.password = password;
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("setUsernamePassword" + Thread.currentThread().getName() + " end time:" + System.currentTimeMillis());
        }
    }

    public void addServiceMethod(String data) {
        synchronized (strings) {
            if (strings.size() < 1) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                strings.add(data);
            }
        }
    }

    public int getSize() {
        return strings.size();
    }
}
