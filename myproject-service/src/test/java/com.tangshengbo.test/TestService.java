package com.tangshengbo.test;

import com.tangshengbo.model.Account;
import com.tangshengbo.service.AccountService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    protected final Log log = LogFactory.getLog(TestService.class);
    @Autowired
    private AccountService accountService;
    @Test
    public void test(){
        Account account = new Account();
        account.setMoney(232.3);
        account.setName("locations");
        accountService.addAccount(account);

        List<Account> list = accountService.getAccountAll();
        for (Account a : list){
            log.info(a.toString());
        }
        log.info(accountService.getAccount(1));
        ;
    }

}
