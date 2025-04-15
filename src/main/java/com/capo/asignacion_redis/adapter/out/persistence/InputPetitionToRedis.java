package com.capo.asignacion_redis.adapter.out.persistence;

import org.redisson.api.RMapCacheReactive;
import org.redisson.api.RMapReactive;

import com.capo.asignacion_redis.adapter.in.model.GraphObjectModel;

import reactor.core.publisher.Mono;

public interface InputPetitionToRedis {
	RMapReactive<String,String> getReactiveMap(String mapName);
	Mono<GraphObjectModel> getReactiveJsonBucket(String jsonName);
	RMapCacheReactive <String,String> getRMapCahe(String mapName);
}
