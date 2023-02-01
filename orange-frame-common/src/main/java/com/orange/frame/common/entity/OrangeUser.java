package com.orange.frame.common.entity;

import lombok.Data;

import java.util.List;

/**
 * 用户
 */
@Data
public class OrangeUser {
    /**
     * id
     */
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 是否启用
     */
    private boolean enabled;
    /**
     * 是否未过期
     */
    private boolean accountNonExpired;
    /**
     * 是否未锁定
     */
    private boolean accountNonLocked;
    /**
     * 是否密码未过期
     */
    private boolean credentialsNonExpired;
    /**
     * 角色列表
     */
    private List<OrangeRole> roleList;

}
