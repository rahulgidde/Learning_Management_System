package com.bridgelabz.lmsapplication.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class RedisUtilImpl implements IRedisUtil, Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void save(String redisKey, String userName, String token) {
        redisTemplate.opsForHash().put(redisKey, userName, token);
    }

    @Override
    public Object getRedisToken(String redisKey, String userName) {
        return redisTemplate.opsForHash().get(redisKey, userName);
    }
}
