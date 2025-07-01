package com.hirrao.javaee.service;

import org.springframework.stereotype.Service;

@Service
public interface RedisService {
    void set(String key, String value);

    String get(String key);
}
