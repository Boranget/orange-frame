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
    String id;
    /**
     * 菜单名
     */
    String name;
    /**
     * 权限标识
     */
    String permission;
    /**
     * 对应地址
     */

    String pattern;
    /**
     * 父级菜单
     */
    String parent;
    /**
     * 排序字段
     */
    Long sort;
    /**
     * 描述
     */
    String desc;
    /**
     * 子菜单列表
     */
    List<OrangeMenu> subMenuList;

}
