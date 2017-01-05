package com.tangshengbo.service;

import com.tangshengbo.dao.RoleMapper;
import com.tangshengbo.dao.UserMapper;
import com.tangshengbo.model.Role;
import com.tangshengbo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Cacheable(value = "userRoleServiceCache", condition = "#userName.equals('user')")
    @Override
    public User getUserByLoginName(String userName) {
        logger.info("getUserByLoginName start>>>>>>>>>>>>>>>>>>>>>>{}", userName);

        User user = userMapper.getUserByLoginName(userName);

        logger.info("getUserByLoginName end>>>>>>>>>>>>>>>>>>>>>>{}", user.toString());

        return user;
    }

    @Override
    public List<Role> getRolesByLoginName(String userName) {
        logger.info("getRolesByLoginName start>>>>>>>>>>>>>>>>>>>>>>{}", userName);

        List<Role> roles = roleMapper.getRolesByLoginName(userName);

        logger.info("getRolesByLoginName end>>>>>>>>>>>>>>>>>>>>>>{}", roles.toString());

        return roles;
    }

    @CacheEvict(value = "userRoleServiceCache", allEntries = true)
    @Override
    public void reload() {

        logger.info("reload user>>>>>>>>>>>>>>>>>>>>>>>>>>>");

    }

    @CachePut(value = "userRoleServiceCache")
    @Override
    public User updateUserByName(String userName) {
        logger.info("updateUserByName start>>>>>>>>>>>>>>>>>>>>>>{}", userName);

        User user = null;

        int result = userMapper.updateUserByName(userName);
        if (result >= 1) {
            user = userMapper.getUserByLoginName(userName);
        }

        logger.info("updateUserByName end>>>>>>>>>>>>>>>>>>>>>>{}", userName);

        return user;
    }
}
