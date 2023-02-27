package com.orange.frame.job.controller;

import com.alibaba.fastjson2.JSONObject;
import com.orange.frame.job.entity.OrangeJobInfo;
import com.orange.frame.job.mapper.JobInfoMapper;
import com.orange.frame.job.service.JobInfoService;
import com.orange.frame.job.service.ScheduleService;
import com.orange.frame.job.util.ScheduleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author boranget
 * @date 2023/2/24
 */
@RestController
public class JobInfoController {
    @Autowired
    JobInfoService jobInfoService;
    @Autowired
    ScheduleService scheduleService;

    @RequestMapping("/load_all_job")
    public String test() {
        List<OrangeJobInfo> orangeJobInfos = jobInfoService.listAllJobInfo();
        return JSONObject.toJSONString(orangeJobInfos);
    }

    @RequestMapping(value = "/job_info", method = RequestMethod.POST)
    public String saveJobInfo(@RequestBody OrangeJobInfo orangeJobInfo) {
        // 设置当前状态
        orangeJobInfo.setJobStatus(ScheduleEnum.BIRTH.name());
        // 设置创建时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        orangeJobInfo.setCreateTime(format);
        jobInfoService.saveJobInfo(orangeJobInfo);
        scheduleService.addSchedule(orangeJobInfo);
        return JSONObject.toJSONString(orangeJobInfo);
    }

    @RequestMapping(value = "/start_schedule", method = RequestMethod.POST)
    public String startSchedule(@RequestBody Map<String, Object> requestBody) {
        Long jobInfoId = Long.valueOf((String) requestBody.get("jobInfoId"));
        // TODO 修改数据库中job信息的状态
        scheduleService.startSchedule(jobInfoId);
        return "success";
    }

    @RequestMapping(value = "/standby_schedule", method = RequestMethod.POST)
    public String standbySchedule(@RequestBody Map<String, Object> requestBody) {
        Long jobInfoId = Long.valueOf((String) requestBody.get("jobInfoId"));
        scheduleService.standbySchedule(jobInfoId);
        return "success";
    }

    @RequestMapping(value = "/shutdown_schedule", method = RequestMethod.POST)
    public String shutdownSchedule(@RequestBody Map<String, Object> requestBody) {
        Long jobInfoId = Long.valueOf((String) requestBody.get("jobInfoId"));
        scheduleService.shutdownSchedule(jobInfoId, true);
        return "success";
    }
}
