package com.capo.asignacion_redis.adapter.out.persistence.startingApp;

import org.redisson.api.RMapReactive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capo.asignacion_redis.adapter.enums.RedisEnum;
import com.capo.asignacion_redis.adapter.out.model.PointRedisModel;
import com.capo.asignacion_redis.adapter.out.model.VertexRedisModel;
import com.capo.asignacion_redis.adapter.out.persistence.redisPetition.InputPetitionToRedis;


@Service
public class OperationsInRedisStartingApp implements OperationsInRedisStarting {
	
	@Autowired
	InputPetitionToRedis petitionRedis;
	
	@Override
	public String saveAndUpdateCostAndDestinationStartingApp(VertexRedisModel vertexRedisModel) {
		String key= vertexRedisModel.getStartVertex()+","+vertexRedisModel.getEndVertex();
		RMapReactive<String,String> map = this.petitionRedis.getReactiveMap(RedisEnum.MAP_COST.value);
		map.put(key, vertexRedisModel.getCost()).then().subscribe();
		return "OK";
	}
	
	@Override
	public PointRedisModel savePointsOfSaleStartingApp(PointRedisModel pointRedisModel) {
		RMapReactive<String,String> map = this.petitionRedis.getReactiveMap(RedisEnum.MAP_STORES.value);
		map.put(pointRedisModel.getLocation(),pointRedisModel.getId()).then().subscribe();
		return pointRedisModel;
	}
}
