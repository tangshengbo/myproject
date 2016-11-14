package com.tangshengbo.service;

import com.tangshengbo.dao.AccountMapper;
import com.tangshengbo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    protected final Log log = LogFactory.getLog(AccountServiceImpl.class);
	@Autowired
	private AccountMapper accountMapper;

	public void addAccount(Account account) {
		accountMapper.insert(account);
        log.info("add account success{}");
	}
	public Account getAccount(Integer id) {
		Account account = accountMapper.selectByPrimaryKey(id);
        log.info("serach account success");
		return account;
	}

	public List<Account> getAccountAll() {
        log.info("getAccountAll account success");
		return accountMapper.getAccounts();
	}

}
