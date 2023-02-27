package com.orange.frame.job.service.impl;

import com.orange.frame.job.config.OrangeJobInstance;
import com.orange.frame.job.entity.OrangeJobInfo;
import com.orange.frame.job.service.ScheduleService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author boranget
 * @date 2023/2/27
 */
@Service
public class ScheduleServiceImpl implements ScheduleService  {
    private Map<Long, Scheduler> scheduleMap;

    private ScheduleServiceImpl() {
        scheduleMap = new HashMap<>();
    }


    @Override
    public void addSchedule(OrangeJobInfo orangeJobInfo) {
        Scheduler scheduler = generateSchedulerByJobInfo(orangeJobInfo);
        scheduleMap.put(orangeJobInfo.getJobId(),scheduler);
    }

    @Override
    public void removeSchedule(Long jobInfoId, boolean immediately) {
        shutdownSchedule(jobInfoId,immediately);
        scheduleMap.remove(jobInfoId);
    }

    @Override
    public void modifySchedule(OrangeJobInfo orangeJobInfo, boolean immediately) {
        // 先移除
        removeSchedule(orangeJobInfo.getJobId(),immediately);
        // 再添加
        addSchedule(orangeJobInfo);
    }

    @Override
    public void startSchedule(Long jobInfoId) {
        Scheduler scheduler = scheduleMap.get(jobInfoId);
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void standbySchedule(Long jobInfoId) {
        Scheduler scheduler = scheduleMap.get(jobInfoId);
        try {
            scheduler.standby();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdownSchedule(Long jobInfoId, boolean immediately) {
        Scheduler scheduler = scheduleMap.get(jobInfoId);
        try {
            scheduler.shutdown(immediately);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据JobInfo生成一个Sechdule
     * @param orangeJobInfo
     * @return
     * @throws SchedulerException
     */
    private Scheduler generateSchedulerByJobInfo(OrangeJobInfo orangeJobInfo) {
        // 组装 JobDetail
        JobDetail jobDetail = JobBuilder.newJob(OrangeJobInstance.class)
                .withIdentity(orangeJobInfo.getJobName(), orangeJobInfo.getJobGroup())
                .build();
        // 传递信息
        jobDetail.getJobDataMap().put("orangeJobInfo", orangeJobInfo);
        // Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(orangeJobInfo.getJobName(), orangeJobInfo.getJobGroup())
                .withSchedule(CronScheduleBuilder.cronSchedule(orangeJobInfo.getCorn()))
                .build();
        // Scheduler
        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return scheduler;
    }
}
