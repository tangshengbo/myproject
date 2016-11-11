package com.tangshengbo.test;

import com.tangshengbo.model.Account;
import com.tangshengbo.service.AccountService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml","classpath:spring-mybatis.xml"})
public class TestService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private AccountService accountService;
    @Test
    public void test(){
        Account account = new Account();
        account.setMoney(232.3);
        account.setName("locations");
        accountService.addAccount(account);
        log.info("addAccount");
        List<Account> list = accountService.getAccountAll();
        for (Account a : list){
            log.info(a);
        }
    }

}
