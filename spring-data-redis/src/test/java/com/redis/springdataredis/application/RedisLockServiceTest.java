package com.redis.springdataredis.application;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class RedisLockServiceTest {

  @Autowired
  private RedisLockService redisLockService;

//  @Test
  void lockTest() {
    final ForkJoinPool forkJoinPool = new ForkJoinPool(5);
    forkJoinPool.execute(() -> {
      IntStream.range(0, 5)
          .parallel()
          .forEach(i -> redisLockService.process(i));
    });
  }
}
