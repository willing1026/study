package com.redis.springdataredis.repository;

import com.redis.springdataredis.domain.RedisData;
import org.springframework.data.repository.CrudRepository;

public interface RedisDataRepository extends CrudRepository<RedisData, Long> {

}
