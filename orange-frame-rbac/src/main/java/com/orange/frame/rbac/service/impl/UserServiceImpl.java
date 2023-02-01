package com.orange.frame.rbac.service.impl;

import com.orange.frame.common.entity.OrangeUser;
import com.orange.frame.rbac.mapper.UserMapper;
import com.orange.frame.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public OrangeUser loadUserByUsername(String username) {
        return userMapper.loadUserByUsername(username);
    }
}
