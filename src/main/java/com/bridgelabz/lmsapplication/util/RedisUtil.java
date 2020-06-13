package com.bridgelabz.lmsapplication.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class RedisUtil implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void save(String rediskey, String userName, String token) {
        redisTemplate.opsForHash().put(rediskey, userName, token);
    }

    public Object getRedisToken(String rediskey, String userName) {
        return redisTemplate.opsForHash().get(rediskey, userName);
    }
}
