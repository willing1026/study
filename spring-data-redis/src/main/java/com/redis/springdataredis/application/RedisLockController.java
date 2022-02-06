package com.redis.springdataredis.application;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis/lock")
@RequiredArgsConstructor
public class RedisLockController {

  private final RedisLockService redisLockService;

  @GetMapping
  public void lock() {
    ForkJoinPool pool = new ForkJoinPool(10);
    pool.execute(() -> {
      IntStream.range(0, 10)
          .parallel()
          .forEach(i -> redisLockService.process(i));
    });
  }
}
