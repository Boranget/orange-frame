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
    private Long id;
    /**
     * 角色名
     */
    private String name;
    /**
     * 描述
     */
    private String desc;
    /**
     * 派生角色
     */
    private List<OrangeRole> derivedRoleList;
    /**
     * 基角色
     */
    private List<OrangeRole> baseRoleList;
    /**
     * 菜单列表
     */
    private  List<OrangeMenu> menuList;
    /**
     * 接口列表
     */
    private  List<OrangeApi> apiList;


}
