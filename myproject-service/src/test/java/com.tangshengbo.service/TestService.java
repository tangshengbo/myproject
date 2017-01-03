package com.tangshengbo.service;

import com.tangshengbo.model.Account;
import com.tangshengbo.model.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserInfoService userInfoService;


    @Test
    public void testAccount(){
        Account account = new Account();
        account.setMoney(232.3);
        account.setName("locations");
        accountService.addAccount(account);

        List<Account> list = accountService.getAccountAll();
        for (Account a : list){
            logger.info(a.toString());
        }
       logger.info("Account:{}",accountService.getAccount(1));

    }
    @Test
    public void testUser(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("tang");
        userInfo.setUserPwd("4123");
        logger.info("register UserInfo:{}", userInfoService.register(userInfo));
        logger.info(" login UserInfo:{}",userInfoService.login(userInfo));
    }

}
