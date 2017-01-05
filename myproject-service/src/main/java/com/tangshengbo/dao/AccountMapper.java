package com.tangshengbo.dao;

import com.tangshengbo.model.Account;

import java.util.List;

public interface AccountMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    List<Account> getAccounts();
}
