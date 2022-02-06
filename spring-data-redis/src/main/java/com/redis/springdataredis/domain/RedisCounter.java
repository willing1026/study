package com.redis.springdataredis.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@RedisHash(value = "redis_counter")
public class RedisCounter {

  @Id
  private Long id;

  private Integer count;

  public void count() {
    if (count == null) count = 0;
    this.count++;
  }
}
