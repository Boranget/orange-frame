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
    private Long id;
    /**
     * 接口名
     */
    private String name;
    /**
     * 权限标识
     */
    private  String permission;
    /**
     * 对应的地址
     */
    private String pattern;
    /**
     * 描述
     */
    private  String desc;
    /**
     * 访问该api所需角色
     */
    private List<OrangeRole> roleList;
}
