package com.orange.frame.job.service;

import com.orange.frame.job.entity.OrangeJobInfo;

import java.util.List;

/**
 * @author boranget
 * @date 2023/2/27
 */
public interface JobInfoService {
    public List<OrangeJobInfo> listAllJobInfo();
    public void saveJobInfo(OrangeJobInfo orangeJobInfo);
}
