package com.orange.frame.rbac.runner;

import com.orange.frame.common.entity.OrangeApi;
import com.orange.frame.rbac.service.RoleApiService;
import com.orange.frame.redis.redis.RedisConstant;
import com.orange.frame.redis.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author boranget
 * @date 2023/2/2
 * 在本服务启动时
 * 将所有api-role映射关系存放到数据库
 */
@Component
@Order(1)
public class InitApiToRedisRunner implements ApplicationRunner {
    @Autowired
    RoleApiService roleApiService;
    @Autowired
    RedisService redisService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<OrangeApi> orangeApis = roleApiService.selectAllApiWithRole();
        redisService.sAdd(RedisConstant.API_ROLE_KEY,orangeApis.toArray());
    }
}
