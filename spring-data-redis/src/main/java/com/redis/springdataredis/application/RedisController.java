package com.redis.springdataredis.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisController {

  private final RedisService redisService;

  @GetMapping("/keys")
  public List<String> keys() {
    return redisService.keys();
  }

  @GetMapping("/{key}")
  public String value(@PathVariable("key") String key) {
    return redisService.value(key);
  }

  @PostMapping("/{key}")
  public void save(@PathVariable("key") String key, @RequestBody String value) {
    redisService.save(key, value);
  }
}
