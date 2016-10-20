package com.tangshengbo.service;

import java.util.List;

import com.tangshengbo.model.Account;

public interface AccountService {
	
	public void addAccount(Account account);
	
	public Account getAccount(Integer id);
	
	public List<Account> getAccountAll();



}
