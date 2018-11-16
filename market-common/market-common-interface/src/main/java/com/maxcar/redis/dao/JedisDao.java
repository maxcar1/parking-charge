package com.maxcar.redis.dao;

import java.util.List;

/**
 * @program: maxcarredismaxcar-redis
 * @description: redis缓存服务接口
 * @author: 罗顺锋
 * @create: 2018-04-19 16:32
 **/
public interface JedisDao {
    /**
     * 根据Key获取值
     * @param key
     * @return
     */
    public String get(String key);

    /**
     * 设置key，value
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value);

    /**
     * 通过哈希获取值
     * @param hkey
     * @param key
     * @return
     */
    public String hget(String hkey, String key) ;

    /**
     * 设置哈希键值对
     * @param hkey
     * @param key
     * @param value
     * @return
     */
    public long hset(String hkey, String key, String value) ;

    /**
     * 将 key 中储存的数字值增一。
     * @param key
     * @return
     */
    public long incr(String key);

    /**
     * 设置key缓存时长
     * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
     * @param key
     * @param second 秒
     * @return
     */
    public long expire(String key, int second);

    /**
     * 通过key获取该参数剩余时长
     * 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。
     * @param key
     * @return
     */
    public long ttl(String key);

    /**
     * 删除键值对
     * @param key
     * @return
     */
    public long del(String key);

    /**
     * 删除哈希键值对
     * @param hkey
     * @param key
     * @return
     */
    public long hdel(String hkey, String key);

    /**
     * 将一个或多个值 value 插入到列表 key 的表头
     * @param key
     * @param string
     * @return
     */
    public long lpush(String key,String string);

    /**
     * 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> lrange(String key, Long start, Long end);
}
