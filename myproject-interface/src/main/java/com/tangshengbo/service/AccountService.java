package com.tangshengbo.service;

import java.util.List;

import com.tangshengbo.model.Account;

public interface AccountService {
	
    void addAccount(Account account);
	
    Account getAccount(Integer id);
	
    List<Account> getAccountAll();



}
