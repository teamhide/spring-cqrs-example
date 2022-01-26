package com.example.springcqrsexample.user.repository;

import com.example.springcqrsexample.user.domain.RedisUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRedisRepository extends CrudRepository<RedisUser, Long> {

}
