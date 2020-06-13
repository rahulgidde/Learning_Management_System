package com.bridgelabz.lmsapplication.util;

public interface IRedisUtil {
    public void save(String redisKey, String userName, String token);

    public Object getRedisToken(String redisKey, String userName);
}
