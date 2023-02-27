package com.orange.frame.rbac.controller;


import com.orange.frame.common.CommonResult;
import com.orange.frame.common.entity.OrangeApi;
import com.orange.frame.common.entity.OrangeUser;
import com.orange.frame.rbac.service.RoleApiService;
import com.orange.frame.rbac.service.UserRoleService;
import com.orange.frame.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RbacController {
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RoleApiService roleApiService;

    /**
     * 根据用户名查找用户信息
     * @param username
     * @return
     */
    @RequestMapping(value = "/load_user_by_username",method = RequestMethod.GET)
    public OrangeUser loadUserByUsername(@RequestParam("username")String username){
        return userService.loadUserByUsername(username);
    }

    /**
     * 根据用户名查找用户信息，但是携带角色信息
     * @param username
     * @return
     */
    @RequestMapping(value = "/select_user_with_roles",method = RequestMethod.GET)
    public OrangeUser selectUserWithRoles(@RequestParam("username")String username){
        return userRoleService.selectUserWithRoles(username);
    }

    /**
     * 查找出所有api，携带角色信息
     * 用于redis网关鉴权
     * @return
     */
    @RequestMapping(value = "/select_all_api_with_role", method = RequestMethod.GET)
    public List<OrangeApi> selectAllApiWithRole(){
        return roleApiService.selectAllApiWithRole();
    }
}
