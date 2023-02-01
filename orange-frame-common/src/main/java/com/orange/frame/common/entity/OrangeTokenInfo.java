package com.orange.frame.common.entity;

import lombok.Data;

/**
 * redis中存储的token信息
 * @author boranget
 * @date 2023/2/1
 */
@Data
public class OrangeTokenInfo {
    private Long expireTime;
    private OrangeUser orangeUser;
}
