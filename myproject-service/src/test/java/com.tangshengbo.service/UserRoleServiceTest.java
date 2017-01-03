package com.tangshengbo.service;

import com.tangshengbo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tangshengbo on 2017/1/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml", "classpath:spring-mybatis.xml"})
public class UserRoleServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRoleService userRoleService;

    @Test
    public void testGetUserByName() {

        {
            logger.info("testGetUserByName start>>>>>>>>>>>>>>>>>>>query first ");

            User user =  userRoleService.getUserByLoginName("user");

            logger.info("Get User:{}",user.toString());

            logger.info("testGetUserByName end>>>>>>>>>>>>>>>>>>>>query first");
        }

        {
            logger.info("testGetUserByName start>>>>>>>>>>>>>>>>>>>query  second ");

            User user =  userRoleService.getUserByLoginName("user");

            logger.info("Get User:{}",user.toString());

            logger.info("testGetUserByName end>>>>>>>>>>>>>>>>>>>>query second ");
        }

    }


}
