package com.orange.frame.rbac.mapper;

import com.orange.frame.common.entity.OrangeApi;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author boranget
 * @date 2023/2/1
 */
@Mapper
public interface RoleApiMapper {
    public List<OrangeApi> selectAllApiWithRole();
}
