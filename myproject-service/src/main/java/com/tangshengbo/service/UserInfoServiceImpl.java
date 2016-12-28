package com.tangshengbo.service;

import com.tangshengbo.dao.UserInfoMapper;
import com.tangshengbo.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Created by Administrator on 2016/11/24.
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService{

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public boolean login(UserInfo userInfo){
        logger.info("login  param {}",userInfo.toString());

        int findResult = userInfoMapper.selectForLogin(userInfo);

        logger.info("login  result {}",findResult);

        return findResult>=1;
    }

    @Override
    public boolean register(UserInfo userInfo) {
        logger.info("register inParam{}",userInfo.toString());

        int registerResult = userInfoMapper.insertSelective(userInfo);

        logger.info("register result {}",registerResult);

        return registerResult>=1;
    }
}
