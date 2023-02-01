package com.orange.frame.rbac.mapper;

import com.orange.frame.common.entity.OrangeUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public OrangeUser loadUserByUsername(String username);
}
