package com.capo.asignacion_redis.adapter.out.persistence.redisPetition;

import org.redisson.api.RMapCacheReactive;
import org.redisson.api.RMapReactive;

import com.capo.asignacion_redis.adapter.in.model.GraphObjectModel;
import com.capo.asignacion_redis.application.domain.model.GraphModel;

import reactor.core.publisher.Mono;

public interface BasicPetitionToRedis {
	RMapReactive<String,String> getReactiveMap(String mapName);
	Mono<GraphObjectModel> getReactiveJsonBucket(String jsonName);
	RMapCacheReactive <String,String> getRMapCahe(String mapName);
	String saveReactiveJsonBucket(String jsonName, GraphModel graph);
}
