package com.orange.frame.job.service.impl;

import com.orange.frame.job.entity.OrangeJobInfo;
import com.orange.frame.job.mapper.JobInfoMapper;
import com.orange.frame.job.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author boranget
 * @date 2023/2/27
 */
@Service
public class JobInfoServiceImpl implements JobInfoService {
    @Autowired
    private JobInfoMapper jobInfoMapper;
    @Override
    public List<OrangeJobInfo> listAllJobInfo() {
        return jobInfoMapper.listAllJobInfo();
    }

    @Override
    public void saveJobInfo(OrangeJobInfo orangeJobInfo) {
        jobInfoMapper.saveJobInfo(orangeJobInfo);

    }
}
