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
    @RequestMapping(value = "/loadUserByUsername",method = RequestMethod.GET)
    public OrangeUser loadUserByUsername(@RequestParam("username")String username){
        return userService.loadUserByUsername(username);
    }
    @RequestMapping(value = "/selectUserWithRoles",method = RequestMethod.GET)
    public OrangeUser selectUserWithRoles(@RequestParam("username")String username){
        return userRoleService.selectUserWithRoles(username);
    }
    @RequestMapping(value = "/selectAllApiWithRole", method = RequestMethod.GET)
    public List<OrangeApi> selectAllApiWithRole(){
        return roleApiService.selectAllApiWithRole();
    }
}
