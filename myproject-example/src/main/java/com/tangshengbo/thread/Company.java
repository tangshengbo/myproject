package com.tangshengbo.thread;

import java.util.concurrent.locks.Lock;

public class Company implements Runnable {

    private Lock lock;

    private Account account;

    public Company(Account account, Lock lock) {
        this.account = account;
        this.lock = lock;
    }

    @Override
    public void run() {
//		    synchronized (account) {
//			System.out.println("开始存钱。。。。。。。。。。。。");
//			for (int i = 0; i < 1000; i++) {
//				
//				account.addAmount(1);
//			}
//			System.out.println("结束存钱。。。。。。。。。。。。");
//		}
        lock.lock();
        try {

            System.out.println("开始存钱。。。。。。。。。。。。");
            for (int i = 0; i < 1000; i++) {

                account.addAmount(1);
            }
            System.out.println("结束存钱。。。。。。。。。。。。");

        } finally {
            lock.unlock();
        }

    }

}
