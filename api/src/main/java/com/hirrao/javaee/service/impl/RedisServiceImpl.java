package com.hirrao.javaee.service.impl;

import com.hirrao.javaee.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    private final long DEFAULT_TIMEOUT = 20;
    private final TimeUnit DEFAULT_TIMEUNIT = TimeUnit.MINUTES;
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisServiceImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value, DEFAULT_TIMEOUT, DEFAULT_TIMEUNIT);
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
