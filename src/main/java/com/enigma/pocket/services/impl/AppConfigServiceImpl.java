package com.enigma.pocket.services.impl;

import com.enigma.pocket.services.AppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AppConfigServiceImpl implements AppConfigService {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public String getValue(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public HashMap<String, Object> getMap(String key) {
        return null;
    }
}
