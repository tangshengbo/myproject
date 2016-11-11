package com.tangshengbo.service;

import com.tangshengbo.dao.AccountMapper;
import com.tangshengbo.model.Account;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private AccountMapper accountMapper;

	public void addAccount(Account account) {
		accountMapper.insert(account);
        log.info("add account success");
		System.out.println("add account success");
	}
	public Account getAccount(Integer id) {
		Account account = accountMapper.selectByPrimaryKey(id);
		System.out.println("serach account success");
		return account;
	}

	public List<Account> getAccountAll() {
        log.info("getAccountAll account success");
		return accountMapper.getAccounts();
	}

}
