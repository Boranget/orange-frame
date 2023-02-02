package com.orange.frame.common.entity;

import lombok.Data;

import java.util.List;

/**
 * 接口
 * @author boranget
 */
@Data
public class OrangeApi {
    /**
     * id
     */
    String id;
    /**
     * 接口名
     */
    String name;
    /**
     * 权限标识
     */
    String permission;
    /**
     * 对应的地址
     */
    String pattern;
    /**
     * 描述
     */
    String desc;
    /**
     * 访问该api所需角色
     */
    List<OrangeRole> roleList;
}
