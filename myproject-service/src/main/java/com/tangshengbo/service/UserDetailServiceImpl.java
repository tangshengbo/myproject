package com.tangshengbo.service;

import com.tangshengbo.dao.UserInfoMapper;
import com.tangshengbo.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Administrator on 2016/11/25.
 */
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails details = null;
        try {
            // 用户名,密码,是否激活,accountnonexpired如果帐户没有过期设置为true
            // credentialsnonexpired如果证书没有过期设置为true
            // accountnonlocked如果帐户不锁定设置为true
            UserInfo u = this.getUser(username);

            //目前是把角色给写死了
            details = new org.springframework.security.core.userdetails.User(u.getUserName(), u.getUserPwd(), AuthorityUtils.createAuthorityList("ROLE_USER"));

        } catch (UsernameNotFoundException usernameNotFoundException) {
            usernameNotFoundException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;


    }

    public UserInfo getUser(String username) {
        return userInfoMapper.selectByName(username);
    }
}
