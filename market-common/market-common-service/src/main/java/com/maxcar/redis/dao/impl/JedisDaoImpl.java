package com.maxcar.redis.dao.impl;

import com.maxcar.redis.dao.JedisSingleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @program: maxcar-util
 * @description: 单机版redis实现类
 * @author: 罗顺锋
 * @create: 2018-04-19 18:37
 **/
@Component
public class JedisDaoImpl implements JedisSingleDao {
    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource(name = "longRedisTemplate")
    private RedisTemplate<String, Long> longRedisTemplate;

    @Override
    public Long getLongValue(String key) throws Exception {
        ValueOperations<String, Long> opsForValue = longRedisTemplate.opsForValue();
        return opsForValue.get(key);
    }

    @Override
    public String get(String key) throws Exception {
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        return opsForValue.get(key);
    }

    @Override
    public void set(String key, String value) throws Exception {
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key, value);
    }

    @Override
    public void increment(String key, long delta) throws Exception {
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        opsForValue.increment(key, delta);
    }

    //		ValueOperations<Serializable, Serializable> opsForValue = redisTemplate.opsForValue();
    //		MICROSECONDS    微秒   一百万分之一秒（就是毫秒/1000）
    //		MILLISECONDS    毫秒   千分之一秒
    //		NANOSECONDS   毫微秒  十亿分之一秒（就是微秒/1000）
    //		SECONDS          秒
    //		MINUTES     分钟
    //		HOURS      小时
    //		DAYS      天
    @Override
    public void expire(String key, int second) throws Exception {
        redisTemplate.expire(key, second, TimeUnit.SECONDS);
//		redisTemplate.expire(key, second,TimeUnit.s);
    }
    //使用PERSIST命令可以清除超时，使其变成一个永久的key。
    @Override
    public void persist(String key)throws Exception{
        redisTemplate.persist(key);
    }

    @Override
    public void del(String key) throws Exception {
        redisTemplate.delete(key);
    }


    /**
     * param:
     * describe: 存储数据至zSet
     * create_date:  lxy   2018/8/28  17:33
     **/
    @Override
    public boolean addObjectValueZSet(String key, Object object, double score) {
        return redisTemplate.boundZSetOps(key).add(object, score);
    }

    /**
     * param:
     * describe: 获取zSet数据 score 倒叙 下标取值
     * create_date:  lxy   2018/8/28  17:35
     **/
    @Override
    public <T> Set<T> getObjectValueZSet(String key, Long startRow, Long endRow) {
        return redisTemplate.boundZSetOps(key).reverseRange(startRow, endRow);
    }

    /**
     * param:
     * describe: zSet 根据score 获取 倒叙第一个 value
     * create_date:  lxy   2018/8/28  17:48
     **/
    @Override
    public <T> T getObjectValueZSetByScore(String key, double score) {

        if (null == redisTemplate.boundZSetOps(key).reverseRangeByScore(score, score)) {
            return null;
        }

        return (T) redisTemplate.boundZSetOps(key).reverseRangeByScore(score, score).iterator().next();
    }

    /**
     * param:
     * describe: key 是否存在
     * create_date:  lxy   2018/8/28  18:36
     **/
    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 获取ZSet长度
     *
     * @param key
     */
    @Override
    public Long getLengthOfZSet(String key) {
        return redisTemplate.boundZSetOps(key).size();
    }

}