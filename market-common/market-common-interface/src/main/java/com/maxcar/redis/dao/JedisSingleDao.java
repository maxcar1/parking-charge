package com.maxcar.redis.dao;

import java.util.Set;

/**
 * @program: maxcar-utils
 * @description: redis缓存，单机版
 * @author: 罗顺锋
 * @create: 2018-04-19 18:35
 **/
public interface JedisSingleDao {

    /**
     * 获取 Long 类型值
     *
     * @param key
     * @return
     * @throws Exception
     */
    Long getLongValue(String key) throws Exception;

    /**
     * 获取
     *
     * @param key
     * @return
     */
    String get(String key) throws Exception;

    /**
     * 设置
     *
     * @param key
     * @param value
     */
    void set(String key, String value) throws Exception;

    /**
     * 自增
     *
     * @param key
     * @param delta
     * @throws Exception
     */
    void increment(String key, long delta) throws Exception;

    /**
     * 追加时间
     *
     * @param key
     * @param second
     */
    void expire(String key, int second) throws Exception;

    //永久有效
    public void persist(String key)throws Exception;

    /**
     * 删除
     *
     * @param key
     */
    void del(String key) throws Exception;

    /**
     * param:
     * describe: 新增数据至zSet
     * create_date:  lxy   2018/8/28  18:04
     **/
    boolean addObjectValueZSet(String key, Object object, double score);

    /**
     * param:
     * describe: 获取zSet数据 score 倒叙 下标取值
     * create_date:  lxy   2018/8/28  17:35
     **/
    <T> Set<T> getObjectValueZSet(String key, Long startRow, Long endRow);

    /**
     * param:
     * describe: zSet 根据score 获取 倒叙第一个 value
     * create_date:  lxy   2018/8/28  17:48
     **/
    <T> T getObjectValueZSetByScore(String key, double score);

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
