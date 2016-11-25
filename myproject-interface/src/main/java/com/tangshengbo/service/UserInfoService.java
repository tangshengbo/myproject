package com.tangshengbo.service;

import com.tangshengbo.model.UserInfo;

/**
 * Created by Administrator on 2016/11/24.
 */
public interface UserInfoService {

     boolean login(UserInfo userInfo);

     boolean register(UserInfo userInfo);



}
