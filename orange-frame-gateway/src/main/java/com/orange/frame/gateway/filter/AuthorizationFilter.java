package com.orange.frame.gateway.filter;

import com.orange.frame.common.entity.OrangeApi;
import com.orange.frame.common.entity.OrangeRole;
import com.orange.frame.common.entity.OrangeTokenInfo;
import com.orange.frame.common.entity.OrangeUser;
import com.orange.frame.gateway.config.FilterWhiteListConfig;
import com.orange.frame.redis.redis.RedisConstant;
import com.orange.frame.redis.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
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
import java.util.Set;

/**
 * 判断当前token是否有权限访问目标接口
 * @author boranget
 * @date 2023/2/1
 */
@Component
@Order(2)
public class AuthorizationFilter implements GlobalFilter {
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
        OrangeTokenInfo tokenInfo = (OrangeTokenInfo) tokenInfoMap.get(token);
        OrangeUser orangeUser = tokenInfo.getOrangeUser();
        List<OrangeRole> userRoleList = orangeUser.getRoleList();
        // 获取访问当前接口所需的角色列表
        // 获取所有接口
        Set<Object> objects = redisService.sMembers(RedisConstant.API_ROLE_KEY);
        for (Object object : objects) {
            OrangeApi orangeApi  = (OrangeApi) object;
            String pattern = orangeApi.getPattern();
            // 如果匹配到，取出其角色列表
            if (pathMatcher.match(pattern, currentRequestUri.getPath())) {
                List<OrangeRole> needRoleList = orangeApi.getRoleList();
                for (OrangeRole needRole : needRoleList) {
                    for (OrangeRole hasRole: userRoleList){
                       // TODO 进行一个角色的匹配
                        boolean can = thisRoleCan(needRole, hasRole);
                        if(can){
                            return chain.filter(exchange);
                        }
                    }
                }
                break;
            }
        }
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }
    public boolean thisRoleCan(OrangeRole needRole, OrangeRole hasRole){
        if(needRole.getId() .equals(hasRole.getId()) ){
            return true;
        }else {
            List<OrangeRole> baseRoleList = hasRole.getBaseRoleList();
            for (OrangeRole baseRole : baseRoleList) {
                if(thisRoleCan(needRole,baseRole)){
                    return true;
                }
            }
        }
        return false;
    }
}
