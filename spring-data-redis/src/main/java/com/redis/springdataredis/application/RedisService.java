package com.redis.springdataredis.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {

  private final RedisTemplate redisTemplate;

  public List<String> keys() {
    return null;
  }

  public String value(String key) {
    return null;
  }

  public void save(String key, String value) {
    final ValueOperations valueOperations = redisTemplate.opsForValue();
    valueOperations.set(key, value);
    log.info("redis set key: {}, value: {}", key, value);
  }
}
