package com.tangshengbo.dao;

import com.tangshengbo.model.UserInfo;

public interface UserInfoMapper {

    int deleteByPrimaryKey(Long userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long userId);

    UserInfo selectByName(String userName);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    int selectForLogin(UserInfo record);
}
