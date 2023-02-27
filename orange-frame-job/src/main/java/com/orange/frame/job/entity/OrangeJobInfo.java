package com.orange.frame.job.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @author boranget
 * @date 2023/2/24
 */

@Data
@Builder
public class OrangeJobInfo {
    /**
     * job id
     */
    Long jobId;
    /**
     * 任务名称
     */
    String jobName;
    /**
     * 任务组
     */
    String jobGroup;
    /**
     * 任务当前状态
     */
    String jobStatus;

    /**
     * 目标地址
     */
    String targetUrl;
    /**
     * 请求头信息
     */
    Map<String,String> headers;
    /**
     * 请求方式
     */
    String method;
    /**
     * 请求参数
     */
    Map<String,String> params;
    /**
     * 请求体
     */
    String body;
    /**
     * corn表达式
     */
    String corn;
    /**
     * 创建者
     */
    Long createUserId;
    /**
     * 创建时间
     */
    String createTime;
}
