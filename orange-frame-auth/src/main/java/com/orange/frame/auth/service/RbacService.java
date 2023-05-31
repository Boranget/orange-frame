package com.orange.frame.auth.service;

import com.orange.frame.common.entity.OrangeUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "orange-frame-rbac")
public interface RbacService {
    @RequestMapping(value = "/load_user_by_username",method = RequestMethod.GET)
    public OrangeUser loadUserByUsername(@RequestParam("username")String username);
    @RequestMapping(value = "/select_user_with_roles",method = RequestMethod.GET)
    public OrangeUser selectUserWithRoles(@RequestParam("username")String username);
}
