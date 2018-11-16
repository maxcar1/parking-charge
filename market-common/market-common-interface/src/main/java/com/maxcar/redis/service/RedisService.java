package com.maxcar.redis.service;


import java.util.Set;

public interface RedisService {

    /**
     * 设置key的过期时间
     *
     * @param key
     * @param second
     */
    void expire(String key, int second);

    /**
     * kv数据结构
     *
     * @param key   缓存key
     * @param value 缓存值
     */
    void set(String key, String value);

    /**
     * kv数据结构
     *
     * @param key    缓存key
     * @param value  缓存值
     * @param second 过期时间 秒
     */
    void set(String key, String value, int second);

    /**
     * 获取缓存值
     *
     * @param key 缓存key
     * @return
     */
    String get(String key);

    /**
     * param:
     * describe: 新增数据至zSet
     * create_date:  lxy   2018/8/28  18:04
     **/
    public boolean addObjectValueZSet(String key, Object object, double score);

    /**
     * param:
     * describe: 获取zSet数据 score 倒叙 下标取值
     * create_date:  lxy   2018/8/28  17:35
     **/
    public <T> Set<T> getObjectValueZSet(String key, Long startRow, Long endRow);

    /**
     * param:
     * describe: zSet 根据score 获取 倒叙第一个 value
     * create_date:  lxy   2018/8/28  17:48
     **/
    public <T> T getObjectValueZSetByScore(String key, double score);

    /**
     * param:
     * describe: 删除缓存
     * create_date:  lxy   2018/8/28  18:12
     **/
    public void del(String key);

    /**
     * param:
     * describe: key是否存在
     * create_date:  lxy   2018/8/28  18:37
     **/
    boolean hasKey(String key);

    /**
     * param:
     * describe: 获取zSet的长度
     * create_date:  lxy   2018/8/28  18:53
     **/
    Long getLengthOfZSet(String key);
}
