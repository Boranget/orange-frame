package com.orange.frame.rbac.service;


import com.orange.frame.common.entity.OrangeUser;

public interface UserRoleService {
    public OrangeUser selectUserWithRoles(String username);
}
