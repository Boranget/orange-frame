package com.orange.frame.job.config;

import com.orange.frame.job.entity.OrangeJobInfo;
import com.orange.frame.job.util.HttpJobClient;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author boranget
 * @date 2023/2/24
 */
public class OrangeJobInstance implements Job {
    OrangeJobInfo orangeJobInfo;

    public void setOrangeJobInfo(OrangeJobInfo orangeJobInfo) {
        this.orangeJobInfo = orangeJobInfo;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(orangeJobInfo.toString());
        HttpJobClient.call(orangeJobInfo);
    }
}
