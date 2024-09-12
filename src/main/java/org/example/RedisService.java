package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private static final String CURRENT_NUMBER_KEY = "currentNumber";

    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;

    public int getCurrentNumber() {
        Integer currentNumber = redisTemplate.opsForValue().get(CURRENT_NUMBER_KEY);
        return currentNumber != null ? currentNumber : 1;
    }

    public void incrementNumber() {
        redisTemplate.opsForValue().increment(CURRENT_NUMBER_KEY);
    }

    public void resetCurrentNumber() {
        redisTemplate.opsForValue().set(CURRENT_NUMBER_KEY, 1);
    }

    public double getMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        double totalMemory = runtime.totalMemory();
        double freeMemory = runtime.freeMemory();
        double usedMemory = totalMemory - freeMemory;
        return (usedMemory / totalMemory) * 100;
    }
}
