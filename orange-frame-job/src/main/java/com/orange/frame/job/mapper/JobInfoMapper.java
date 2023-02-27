package com.orange.frame.job.mapper;

import com.orange.frame.job.entity.OrangeJobInfo;
import org.apache.catalina.LifecycleState;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author boranget
 * @date 2023/2/24
 */
@Mapper
public interface JobInfoMapper {
    public List<OrangeJobInfo> listAllJobInfo();
    public void saveJobInfo(OrangeJobInfo orangeJobInfo);
}
