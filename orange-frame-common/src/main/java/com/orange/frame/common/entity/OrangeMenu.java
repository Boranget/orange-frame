package com.orange.frame.common.entity;

import lombok.Data;

import java.util.List;

/**
 * 菜单
 * @author boranget
 */
@Data
public class OrangeMenu {
    /**
     * id
     */
    private Long id;
    /**
     * 菜单名
     */
    private  String name;
    /**
     * 权限标识
     */
    private  String permission;
    /**
     * 对应地址
     */

    private String pattern;
    /**
     * 父级菜单
     */
    private  String parent;
    /**
     * 排序字段
     */
    private  Long sort;
    /**
     * 描述
     */
    private  String desc;
    /**
     * 子菜单列表
     */
    private  List<OrangeMenu> subMenuList;

}
