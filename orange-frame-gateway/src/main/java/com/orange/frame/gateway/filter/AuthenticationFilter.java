package com.orange.frame.gateway.filter;

import com.orange.frame.common.entity.OrangeTokenInfo;
import com.orange.frame.gateway.config.FilterWhiteListConfig;
import com.orange.frame.redis.redis.RedisConstant;
import com.orange.frame.redis.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * 判断token是否存在以及有效
 *
 * @author boranget
 * @date 2023/2/1
 */
@Component
@Order(1)
public class AuthenticationFilter implements GlobalFilter {
    @Autowired
    RedisService redisService;
    @Autowired
    FilterWhiteListConfig filterWhiteListConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求和响应对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 判断当前对象是否属于过滤范围
        // 若无需过滤则直接放行
        // 可使用白名单
        URI currentRequestUri = request.getURI();
        List<String> urls = filterWhiteListConfig.getUrls();
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String whiteUrl : urls) {
            if (pathMatcher.match(whiteUrl, currentRequestUri.getPath())) {
                return chain.filter(exchange);
            }
        }
        // 获取请求头
        // 获取Authorization值
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst("Authorization");
        // 判断该token是否存在
        Map<Object, Object> tokenInfoMap = redisService.hGetAll(RedisConstant.TOKEN_INFO_KEY);
        boolean theTokenisExist = tokenInfoMap.containsKey(token);
        if (theTokenisExist) {
            // 判断是否过时
            OrangeTokenInfo tokenInfo = (OrangeTokenInfo) tokenInfoMap.get(token);
            Long expireTime = tokenInfo.getExpireTime();
            boolean theTokenIsNotExpire = System.currentTimeMillis() <= expireTime;
            if (theTokenIsNotExpire) {
                // 未过时则放行
                return chain.filter(exchange);
            } else {
                // 这里可以加一个删除过时token操作
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
        } else {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
    }
}
