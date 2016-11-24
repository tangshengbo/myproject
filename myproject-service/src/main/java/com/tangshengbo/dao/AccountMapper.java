package com.tangshengbo.dao;

import com.tangshengbo.model.Account;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public interface AccountMapper {
    public final Log log = LogFactory.getLog(AccountMapper.class);
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
    
    List<Account> getAccounts();
}