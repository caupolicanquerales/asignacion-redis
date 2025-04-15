package com.capo.asignacion_redis.adapter.out.persistence.startingApp;

import org.redisson.api.RMapReactive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capo.asignacion_redis.adapter.enums.RedisEnum;
import com.capo.asignacion_redis.adapter.out.model.PointsRedisModel;
import com.capo.asignacion_redis.adapter.out.model.VertexRedisModel;
import com.capo.asignacion_redis.adapter.out.persistence.redisPetition.InputPetitionToRedis;


@Service
public class OperationsInRedisStartingApp implements OperationsInRedisStarting {
	
	@Autowired
	InputPetitionToRedis petitionRedis;
	
	@Override
	public String saveAndUpdateCostAndDestinationStartingApp(VertexRedisModel request) {
		String key= request.getStartVertex()+","+request.getEndVertex();
		RMapReactive<String,String> map = this.petitionRedis.getReactiveMap(RedisEnum.MAP_COST.value);
		map.put(key, request.getCost()).then().subscribe();
		return "OK";
	}
	
	@Override
	public String savePointsOfSaleStartingApp(PointsRedisModel request) {
		RMapReactive<String,String> map = this.petitionRedis.getReactiveMap(RedisEnum.MAP_STORES.value);
		map.put(request.getLocation(),request.getId()).then().subscribe();
		return "OK";
	}
}
