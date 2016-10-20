package com.tangshengbo.test;

import java.util.concurrent.locks.Lock;

public class Bank implements Runnable{
	private Lock lock ;
	
	private Account account;
	
	public  Bank(Account account,Lock lock) {
		this.account = account;
		this.lock = lock;
		
	}

	@Override
	public  void run() {
		
//		synchronized (account) {
//			System.out.println("开始取钱。。。。。。。。。。。。");
//			for (int i = 0; i <1000; i++) {
//				
//				account.delAmount(1);
//			}
//			System.out.println("结束取钱。。。。。。。。。。。。");
//			
//		}
//		
		
		
		lock.lock();
		try {
			System.out.println("开始取钱。。。。。。。。。。。。");
			for (int i = 0; i <1000; i++) {
				
				account.delAmount(1);
			}
			System.out.println("结束取钱。。。。。。。。。。。。");
		} finally {
			lock.unlock();
		}
	
			
			
	
		
		
	}
	

}
