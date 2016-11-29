package com.tangshengbo.service;

import com.google.common.collect.Lists;
import com.tangshengbo.model.Role;
import com.tangshengbo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * Created by Administrator on 2016/11/25.
 */

public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("loadUserByUsername start>>>>>>>>>>>>>>>>>>>>>>{}", username);
        User user = userRoleService.getUserByLoginName(username);
        List<Role> roles = userRoleService.getRolesByLoginName(username);
        List<GrantedAuthority> grantedAuthorities = this.assemblyGrantedAuthority(roles);
        //用户状态
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), enabled, accountNonExpired,
                                                           credentialsNonExpired, accountNonLocked, grantedAuthorities);

        logger.info("loadUserByUsername end>>>>>>>>>>>>>>>>>>>>>>{}", userDetails);
        return userDetails;

    }

    /**
     * 装配权限集合
     * @param roles
     * @return
     */
    private List<GrantedAuthority> assemblyGrantedAuthority(List<Role> roles) {
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        for (Role role : roles) {
            logger.info("authority is :{}", role.getName());
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return grantedAuthorities;
    }


}
