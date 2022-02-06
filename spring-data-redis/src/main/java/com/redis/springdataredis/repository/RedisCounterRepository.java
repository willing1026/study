package com.redis.springdataredis.repository;

import com.redis.springdataredis.domain.RedisCounter;
import org.springframework.data.repository.CrudRepository;

public interface RedisCounterRepository extends CrudRepository<RedisCounter, Long> {

}
