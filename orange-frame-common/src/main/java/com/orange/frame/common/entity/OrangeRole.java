package com.orange.frame.common.entity;

import lombok.Data;

import java.util.List;

/**
 * 角色
 *
 * @author boranget
 */
@Data
public class OrangeRole {
    /**
     * id
     */
    String id;
    /**
     * 角色名
     */
    String name;
    /**
     * 描述
     */
    String desc;
    /**
     * 派生角色
     */
    List<OrangeRole> derivedRoleList;
    /**
     * 基角色
     */
    List<OrangeRole> baseRoleList;
    /**
     * 菜单列表
     */
    List<OrangeMenu> menuList;
    /**
     * 接口列表
     */
    List<OrangeApi> apiList;


}
