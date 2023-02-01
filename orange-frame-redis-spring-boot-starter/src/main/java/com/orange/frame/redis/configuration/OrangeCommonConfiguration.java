package com.orange.frame.redis.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.orange.frame.redis.redis.RedisService;
import com.orange.frame.redis.redis.RedisServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 启用 redis-service: orange.common.redis-service.enabled = true
 * @author boranget
 * @date 2023/1/31
 */
@Configuration
public class OrangeCommonConfiguration {
    /**
     * 注入自定义的 redisTemplate
     * 若存在配置 orange.common.redis-service.enabled = true 则注入
     * bean名字为orangeRedisTemplate防止冲突
     * @param factory
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "orange.common.redis-service",value = "enabled", matchIfMissing = true)
    public RedisTemplate<String,Object> orangeRedisTemplate(RedisConnectionFactory factory){
        // 要返回的对象初始化
        RedisTemplate<String ,Object> template = new RedisTemplate<>();
        // 设置工厂,这里的工厂对象spring会自动注入
        template.setConnectionFactory(factory);

        // 从这里开始是json序列化器的配置
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer
                = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 以下两条为指定序列化内容
        // 这里必须指定,否则反向序列化结果是map
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL);
        // 序列化器设置om
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 初始化一个字符串的序列化器
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * redisService
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "orange.common.redis-service",value = "enabled", matchIfMissing = true)
    public RedisService redisService(){
        return new RedisServiceImpl();
    }

}
