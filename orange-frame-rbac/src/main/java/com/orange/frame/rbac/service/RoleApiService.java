package com.orange.frame.rbac.service;

import com.orange.frame.common.entity.OrangeApi;

import java.util.List;

/**
 * @author boranget
 * @date 2023/2/1
 */
public interface RoleApiService {
    public List<OrangeApi> selectAllApiWithRole();
}
