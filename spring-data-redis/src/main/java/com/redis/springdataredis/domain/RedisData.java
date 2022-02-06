package com.redis.springdataredis.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@RedisHash(value = "redis_data", timeToLive = 10)
public class RedisData {

  @Id
  private Long id;
  private String value;
}
