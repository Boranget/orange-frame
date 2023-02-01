package com.orange.frame.redis.redis;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author boranget
 * @date 2023/1/31
 */
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate<String, Object> orangeRedisTemplate;

    @Override
    public void set(String key, Object value, long timeout) {
        orangeRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, Object value) {
        orangeRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Object get(String key) {
        return orangeRedisTemplate.opsForValue().get(key);
    }

    @Override
    public Boolean del(String key) {
        return orangeRedisTemplate.delete(key);
    }

    @Override
    public Long del(List<String> keys) {
        return orangeRedisTemplate.delete(keys);
    }

    @Override
    public Boolean expire(String key, long timeout) {
        return orangeRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    @Override
    public Long getExpire(String key) {
        return orangeRedisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    @Override
    public Boolean hasKey(String key) {
        return orangeRedisTemplate.hasKey(key);
    }

    @Override
    public Long incr(String key, long delta) {
        return orangeRedisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public Long decr(String key, long delta) {
        return orangeRedisTemplate.opsForValue().increment(key, -delta);
    }

    @Override
    public Object hGet(String key, String hashKey) {
        return orangeRedisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public Boolean hSet(String key, String hashKey, Object value, long timeout) {
        orangeRedisTemplate.opsForHash().put(key, hashKey, value);
        return expire(key, timeout);
    }

    @Override
    public void hSet(String key, String hashKey, Object value) {
        orangeRedisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public Map<Object, Object> hGetAll(String key) {
        return orangeRedisTemplate.opsForHash().entries(key);
    }

    @Override
    public Boolean hSetAll(String key, Map<String, Object> map, long timeout) {
        orangeRedisTemplate.opsForHash().putAll(key, map);
        return expire(key, timeout);
    }

    @Override
    public void hSetAll(String key, Map<String, ?> map) {
        orangeRedisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public void hDel(String key, Object... hashKey) {
        orangeRedisTemplate.opsForHash().delete(key, hashKey);
    }

    @Override
    public Boolean hHasKey(String key, String hashKey) {
        return orangeRedisTemplate.opsForHash().hasKey(key, hashKey);
    }

    @Override
    public Long hIncr(String key, String hashKey, Long delta) {
        return orangeRedisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    @Override
    public Long hDecr(String key, String hashKey, Long delta) {
        return orangeRedisTemplate.opsForHash().increment(key, hashKey, -delta);
    }

    @Override
    public Set<Object> sMembers(String key) {
        return orangeRedisTemplate.opsForSet().members(key);
    }

    @Override
    public Long sAdd(String key, Object... values) {
        return orangeRedisTemplate.opsForSet().add(key, values);
    }

    @Override
    public Long sAdd(String key, long timeout, Object... values) {
        Long count = orangeRedisTemplate.opsForSet().add(key, values);
        expire(key, timeout);
        return count;
    }

    @Override
    public Boolean sIsMember(String key, Object value) {
        return orangeRedisTemplate.opsForSet().isMember(key, value);
    }

    @Override
    public Long sSize(String key) {
        return orangeRedisTemplate.opsForSet().size(key);
    }

    @Override
    public Long sRemove(String key, Object... values) {
        return orangeRedisTemplate.opsForSet().remove(key, values);
    }

    @Override
    public List<Object> lRange(String key, long start, long end) {
        return orangeRedisTemplate.opsForList().range(key, start, end);
    }

    @Override
    public Long lSize(String key) {
        return orangeRedisTemplate.opsForList().size(key);
    }

    @Override
    public Object lIndex(String key, long index) {
        return orangeRedisTemplate.opsForList().index(key, index);
    }

    @Override
    public Long lPush(String key, Object value) {
        return orangeRedisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public Long lPush(String key, Object value, long timeout) {
        Long index = orangeRedisTemplate.opsForList().rightPush(key, value);
        expire(key, timeout);
        return index;
    }

    @Override
    public Long lPushAll(String key, Object... values) {
        return orangeRedisTemplate.opsForList().rightPushAll(key, values);
    }

    @Override
    public Long lPushAll(String key, Long timeout, Object... values) {
        Long count = orangeRedisTemplate.opsForList().rightPushAll(key, values);
        expire(key, timeout);
        return count;
    }

    @Override
    public Long lRemove(String key, long count, Object value) {
        return orangeRedisTemplate.opsForList().remove(key, count, value);
    }
}
