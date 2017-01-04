package com.tangshengbo.service;

import com.tangshengbo.model.Role;
import com.tangshengbo.model.User;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public interface UserRoleService {
    /**
     * 根据用户登录名获取用户信息
     * @param userName
     * @return
     */
    User getUserByLoginName(String userName);

    List<Role> getRolesByLoginName(String userName);

    void reload();

    User updateUserByName(String userName);

}
