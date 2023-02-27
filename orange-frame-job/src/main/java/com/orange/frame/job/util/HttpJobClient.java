package com.orange.frame.job.util;

import com.orange.frame.job.entity.OrangeJobInfo;
import okhttp3.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author boranget
 * @date 2023/2/24
 * 需要 url,params,headers,method,body
 */
public class HttpJobClient {
    /**
     * 调用接口
     * @param orangeJobInfo
     * @return
     */
    public static String call(OrangeJobInfo orangeJobInfo) {
        OkHttpClient okHttpClient = new OkHttpClient();
        // 拼接请求参数到请求地址
        StringBuilder finallyTargetUrl = new StringBuilder(orangeJobInfo.getTargetUrl());
        Map<String, String> params = orangeJobInfo.getParams();
        if (params != null && !params.isEmpty()) {
            finallyTargetUrl.append("?");
            Set<String> paramKeySet = params.keySet();
            Iterator<String> iterator = paramKeySet.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                finallyTargetUrl.append(key);
                finallyTargetUrl.append("=");
                finallyTargetUrl.append(params.get(key));
                if (iterator.hasNext()) {
                    finallyTargetUrl.append("&");
                }
            }
        }
        // 组装请求
        // 请求方法
        String jobInfoMethod = orangeJobInfo.getMethod();
        // 请求体
        // GET 方法没有请求体
        String orangeJobInfoBody = orangeJobInfo.getBody() == null ? "" : orangeJobInfo.getBody();
        RequestBody requestBody = jobInfoMethod == "GET" ? null : RequestBody.create(orangeJobInfoBody.getBytes(StandardCharsets.UTF_8));
        Request request = new Request.Builder()
                // 目标地址
                .url(finallyTargetUrl.toString())
                .method(jobInfoMethod, requestBody)
                .headers(Headers.of(orangeJobInfo.getHeaders()))
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = null;
        try {
            response = call.execute();
            if (response.code() == 200) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
