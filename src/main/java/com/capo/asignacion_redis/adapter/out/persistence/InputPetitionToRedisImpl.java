package com.capo.asignacion_redis.adapter.out.persistence;
import org.redisson.api.RJsonBucketReactive;
import org.redisson.api.RMapCacheReactive;
import org.redisson.api.RMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.codec.JacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capo.asignacion_redis.adapter.in.model.GraphObjectModel;

import reactor.core.publisher.Mono;

@Repository
public class InputPetitionToRedisImpl implements InputPetitionToRedis{
	
	@Autowired
	private RedissonReactiveClient client;
	
	@Override
	public RMapReactive<String,String> getReactiveMap(String mapName){
		return client.getMap(mapName,StringCodec.INSTANCE);
	}
	
	@Override
	public Mono<GraphObjectModel> getReactiveJsonBucket(String jsonName){
		RJsonBucketReactive<GraphObjectModel> json= client.getJsonBucket(jsonName,new JacksonCodec<>(GraphObjectModel.class));
		return json.get();
	}
	
	@Override
	public RMapCacheReactive <String,String> getRMapCahe(String mapName){
		return client.getMapCache(mapName);
	}
}
