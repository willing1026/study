package com.redis.springdataredis.application;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class RedisServiceTest {

  @Autowired
  private RedisService redisService;
  @Autowired
  private RedisTemplate redisTemplate;

  @Test
  void save() {
    //when
    final String key = "test";
    final String value = "test_value";
    redisService.save(key, value);

    //then
    final ValueOperations valueOperations = redisTemplate.opsForValue();
    assertThat(valueOperations.get(key)).isEqualTo(value);
  }
}
