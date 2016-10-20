package com.tangshengbo.test;

import com.tangshengbo.model.Account;
import com.tangshengbo.service.AccountService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by TangShengBo on 2016-10-19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/resources/*.xml")
public class Test {
    @Autowired
    private AccountService accountService;

    @org.junit.Test
    public void test(){
        Account account = new Account();
        account.setMoney(232.3);
        account.setName("thread");
        accountService.addAccount(account);
        System.out.println("...............");
    }








}
