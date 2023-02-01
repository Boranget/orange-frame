package com.orange.frame.rbac.service.impl;

import com.orange.frame.common.entity.OrangeUser;
import com.orange.frame.rbac.mapper.UserMapper;
import com.orange.frame.rbac.mapper.UserRoleMapper;
import com.orange.frame.rbac.service.UserRoleService;
import com.orange.frame.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public OrangeUser selectUserWithRoles(String username) {
        return userRoleMapper.selectUserWithRoles(username);
    }
}
