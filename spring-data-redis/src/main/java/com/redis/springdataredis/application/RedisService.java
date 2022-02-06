package com.redis.springdataredis.application;

import com.redis.springdataredis.domain.RedisCounter;
import com.redis.springdataredis.domain.RedisData;
import com.redis.springdataredis.repository.RedisCounterRepository;
import com.redis.springdataredis.repository.RedisDataRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {

  private final RedisTemplate redisTemplate;
  private final RedisDataRepository redisDataRepository;
  private final RedisCounterRepository redisCounterRepository;

  public List<String> keys() {
    return null;
  }

  public String value(String key) {
    return null;
  }

  /**
   * redisTemplate을 이용한 데이터 등록
   * @param key
   * @param value
   */
  public void addWithRedisTemplate(String key, String value) {
    final ValueOperations valueOperations = redisTemplate.opsForValue();
    valueOperations.set(key, value);
    log.info("redis set key: {}, value: {}", key, value);
  }

  /**
   * redisRepository를 이용한 데이터 등록
   * @param id
   * @param value
   */
  public RedisData addWithRedisRepository(Long id, String value) {
    return redisDataRepository.save(RedisData.builder()
        .id(id)
        .value(value)
        .build());
  }

  @Synchronized
  public void getAndAdd() {
    final Optional<RedisCounter> maybe = redisCounterRepository.findById(1L);
    if (maybe.isEmpty()) {
      redisCounterRepository.save(RedisCounter.builder().id(1L).count(1).build());
      return;
    }

    final RedisCounter redisCounter = maybe.get();
    redisCounter.count();
    redisCounterRepository.save(redisCounter);
  }
}
