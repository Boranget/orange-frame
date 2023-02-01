package com.orange.frame.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 网关过滤白名单
 * @author boranget
 * @date 2023/2/1
 */
@Data
@Component
@ConfigurationProperties(prefix = "orange.gateway.filter-white-list")
public class FilterWhiteListConfig {
    private List<String> urls;
}
