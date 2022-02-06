package com.redis.springdataredis.application;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisLockService {

  private final RedissonClient redissonClient;

  public void process(int i) {
    log.info("Process Request : {}", i);
    var lock = redissonClient.getLock("testLock");
    try {
      if (lock.tryLock(4_000, 300_000, TimeUnit.MILLISECONDS)) {
        try {
          var value = redissonClient.getAtomicLong("key");
          log.debug("add and get : {}", value.incrementAndGet());
          Thread.sleep(5_000);
        } finally {
          lock.unlock();
        }
      } else {
        log.error("Lock 획득 실패 - 대기시간 초과");
      }
    } catch (InterruptedException e) {
      log.error("Lock 획득시도 실패");
    }
  }
}
