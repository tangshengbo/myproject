package com.tangshengbo.commons;

import com.tangshengbo.model.Account;
import com.tangshengbo.service.AccountService;

public class AccountThread implements Runnable{
	private AccountService accountService;
	
	private ThreadLocal threadLocal;
	
	public AccountThread(AccountService accountService){
		this.accountService = accountService;
		
	}



	public void run() {
        System.out.println("runing.................");
        Account account = new Account();
		account.setMoney(232.3);
		account.setName("thread");
		accountService.addAccount(account);
	}

}
