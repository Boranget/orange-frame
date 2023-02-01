package com.orange.frame.rbac.service;


import com.orange.frame.common.entity.OrangeUser;
import org.springframework.stereotype.Service;

public interface UserService {
    public OrangeUser loadUserByUsername(String username);
}
