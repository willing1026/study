package com.redis.springdataredis.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.redis.springdataredis.domain.RedisCounter;
import com.redis.springdataredis.domain.RedisData;
import com.redis.springdataredis.repository.RedisCounterRepository;
import java.util.Optional;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
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
  @Autowired
  private RedisCounterRepository redisCounterRepository;

  @Test
  void save() {
    //when
    final String key = "test";
    final String value = "test_value";
    redisService.addWithRedisTemplate(key, value);

    //then
    final ValueOperations valueOperations = redisTemplate.opsForValue();
    assertThat(valueOperations.get(key)).isEqualTo(value);
  }

  @Test
  void addWithRepository() {
    //when
    final String value = "repo_data";
    final RedisData actual = redisService.addWithRedisRepository(1L, value);

    //then
    assertThat(actual.getValue()).isEqualTo(value);
  }

  @DisplayName("redis repository transaction test - 보장 안함")
  @Test
  void getAndAdd() {
    IntStream.range(0, 100)
        .parallel()
        .forEach(i -> {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

          System.out.println("Try : " + i);
          final Optional<RedisCounter> maybe = redisCounterRepository.findById(1L);
          if (maybe.isEmpty()) {
            redisCounterRepository.save(RedisCounter.builder().id(1L).count(1).build());
            return;
          }

          final RedisCounter redisCounter = maybe.get();
          redisCounter.count();
          redisCounterRepository.save(redisCounter);
        });
  }
}
