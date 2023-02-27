package com.orange.frame.job.service;

import com.orange.frame.job.config.OrangeJobInstance;
import com.orange.frame.job.entity.OrangeJobInfo;

/**
 * @author boranget
 * @date 2023/2/27
 */

public interface ScheduleService {
    /**
     * 添加一个Schedule
     * @param orangeJobInfo
     */
     void addSchedule(OrangeJobInfo orangeJobInfo);

    /**
     * 移除一个Schedule
     * @param jobInfoId
     * @param immediately
     */
     void removeSchedule(Long jobInfoId,boolean immediately);

    /**
     * 修改一个Schedule
     * @param orangeJobInfo
     * @param immediately
     */
     void modifySchedule(OrangeJobInfo orangeJobInfo,boolean immediately);


    /**
     * 启动一个Schedule
     * @param jobInfoId
     */
     void startSchedule(Long jobInfoId);

    /**
     * 暂停一个Schedule
     * @param jobInfoId
     */
     void standbySchedule(Long jobInfoId);

    /**
     * 终止一个Schedule
     * @param jobInfoId
     * @param immediately
     */
     void shutdownSchedule(Long jobInfoId, boolean immediately);


}
