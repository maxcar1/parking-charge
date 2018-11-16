package com.maxcar.redis.service.impl;

import com.maxcar.redis.dao.JedisSingleDao;
import com.maxcar.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("redisService")
public class RedisServiceImpl implements RedisService {

    @Autowired
    private JedisSingleDao jedisSingleDao;

    @Override
    public void expire(String key, int second) {
        try {
            jedisSingleDao.expire(key, second);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void set(String key, String value) {
        try {
            jedisSingleDao.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void set(String key, String value, int second) {
        try {
            jedisSingleDao.set(key, value);
            jedisSingleDao.expire(key, second);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String get(String key) {
        try {
            return jedisSingleDao.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean addObjectValueZSet(String key, Object object, double score) {
        return jedisSingleDao.addObjectValueZSet(key, object, score);
    }

    @Override
    public <T> Set<T> getObjectValueZSet(String key, Long startRow, Long endRow) {
        return jedisSingleDao.getObjectValueZSet(key, startRow, endRow);
    }

    @Override
    public <T> T getObjectValueZSetByScore(String key, double score) {
        return jedisSingleDao.getObjectValueZSetByScore(key, score);
    }

    @Override
    public void del(String key) {
        try {
            jedisSingleDao.del(key);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public boolean hasKey(String key) {
        return jedisSingleDao.hasKey(key);
    }

    @Override
    public Long getLengthOfZSet(String key) {
        return jedisSingleDao.getLengthOfZSet(key);
    }
}
