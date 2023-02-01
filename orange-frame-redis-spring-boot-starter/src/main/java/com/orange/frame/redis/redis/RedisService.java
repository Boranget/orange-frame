package com.orange.frame.redis.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author boranget
 * @date 2023/1/31
 */
public interface RedisService {
    /**
     * 保存元素带过期时间
     */
    void set(String key, Object value, long timeout);

    /**
     * 保存元素
     */
    void set(String key, Object value);

    /**
     * 获取元素
     */
    Object get(String key);

    /**
     * 删除元素
     */
    Boolean del(String key);

    /**
     * 批量删除元素
     */
    Long del(List<String> keys);

    /**
     * 设置过期时间
     */
    Boolean expire(String key, long timeout);

    /**
     * 获取过期时间
     */
    Long getExpire(String key);

    /**
     * 判断是否有该元素
     */
    Boolean hasKey(String key);

    /**
     * 按delta自增
     */
    Long incr(String key, long delta);

    /**
     * 按delta自减
     */
    Long decr(String key, long delta);

    /**
     * 获取某个Hash表中的元素
     */
    Object hGet(String key, String hashKey);

    /**
     * 向某个Hash表中放入一个元素带过期时间
     * 注意这里是设置整个hash表的过期时间
     */
    Boolean hSet(String key, String hashKey, Object value, long timeout);

    /**
     * 向某个Hash表中放入一个元素
     */
    void hSet(String key, String hashKey, Object value);

    /**
     * 直接获取整个某个Hash表
     */
    Map<Object, Object> hGetAll(String key);

    /**
     * 直接设置整个某个Hash表带过期时间
     */
    Boolean hSetAll(String key, Map<String, Object> map, long timeout);

    /**
     * 直接设置整个某个Hash表
     */
    void hSetAll(String key, Map<String, ?> map);

    /**
     * 删除某个Hash表中的元素
     */
    void hDel(String key, Object... hashKey);

    /**
     * 判断某个Hash表中是否有该元素
     */
    Boolean hHasKey(String key, String hashKey);

    /**
     * 某个Hash表中某元素自增
     */
    Long hIncr(String key, String hashKey, Long delta);

    /**
     * 某个Hash表中某元素自减
     */
    Long hDecr(String key, String hashKey, Long delta);

    /**
     * 获取某个Set
     */
    Set<Object> sMembers(String key);

    /**
     * 向某个Set中添加元素
     */
    Long sAdd(String key, Object... values);

    /**
     * 向某个Set中添加元素
     */
    Long sAdd(String key, long timeout, Object... values);

    /**
     * 是否为Set中的元素
     */
    Boolean sIsMember(String key, Object value);

    /**
     * 获取某个Set的长度
     */
    Long sSize(String key);

    /**
     * 删除某个Set中的元素
     */
    Long sRemove(String key, Object... values);

    /**
     * 获取某个List中的元素
     */
    List<Object> lRange(String key, long start, long end);

    /**
     * 获取某个List的长度
     */
    Long lSize(String key);

    /**
     * 根据索引获取List中的元素
     */
    Object lIndex(String key, long index);

    /**
     * 向某个List中添加元素
     */
    Long lPush(String key, Object value);

    /**
     * 向某个List中添加元素
     */
    Long lPush(String key, Object value, long timeout);

    /**
     * 向某个List中批量添加元素
     */
    Long lPushAll(String key, Object... values);

    /**
     * 向某个List中批量添加元素
     */
    Long lPushAll(String key, Long timeout, Object... values);

    /**
     * 从某个List中移除元素,value为删除的值，count为要删除几个这样的值
     */
    Long lRemove(String key, long count, Object value);
}
