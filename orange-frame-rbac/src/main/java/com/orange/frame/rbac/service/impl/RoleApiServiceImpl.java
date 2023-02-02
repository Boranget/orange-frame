package com.orange.frame.rbac.service.impl;

import com.orange.frame.common.entity.OrangeApi;
import com.orange.frame.rbac.mapper.RoleApiMapper;
import com.orange.frame.rbac.service.RoleApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author boranget
 * @date 2023/2/1
 */
@Service
public class RoleApiServiceImpl implements RoleApiService {
    @Autowired
    RoleApiMapper roleApiMapper;
    @Override
    public List<OrangeApi> selectAllApiWithRole(){
        return roleApiMapper.selectAllApiWithRole();
    }
}
