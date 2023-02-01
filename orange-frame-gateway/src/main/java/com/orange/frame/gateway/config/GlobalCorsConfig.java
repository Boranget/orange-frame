package com.orange.frame.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * 跨域配置
 * @author boranget
 * @date 2023/2/1
 */
@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        //允许所有请求方式
        config.addAllowedMethod("*");
        //允许所有域
        config.addAllowedOrigin("*");
        //允许所有头信息
        config.addAllowedHeader("*");
        //是否发送cookie信息
        config.setAllowCredentials(true);
        //添加映射路径，拦截一切请求
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);
        //返回新的CorsWebFilter
        return new CorsWebFilter(source);
    }

}
