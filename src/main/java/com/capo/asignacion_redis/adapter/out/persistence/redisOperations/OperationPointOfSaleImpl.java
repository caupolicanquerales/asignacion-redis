package com.capo.asignacion_redis.adapter.out.persistence.redisOperations;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.redisson.api.RMapReactive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capo.asignacion_redis.adapter.enums.RedisEnum;
import com.capo.asignacion_redis.adapter.out.model.PointRedisModel;
import com.capo.asignacion_redis.adapter.out.model.PointsOfSaleModel;
import com.capo.asignacion_redis.adapter.out.persistence.redisPetition.InputPetitionToRedis;

import reactor.core.publisher.Mono;

@Service
public class OperationPointOfSaleImpl implements OperationPointOfSale{
	
	@Autowired
	InputPetitionToRedis petitionRedis;
	
	@Override
	public Mono<PointsOfSaleModel> getPointsOfSale() {
		RMapReactive<String,String> map = this.petitionRedis.getReactiveMap(RedisEnum.MAP_STORES.value);
		return map.entryIterator().map(this::getPointsOfSale)
			.collect(Collectors.toList())
			.map(this::getResponsePointsRedis);
	}
	
	private PointRedisModel getPointsOfSale(Map.Entry<String, String> entry) {
		return getPoint(entry.getKey(),entry.getValue());
	}
	
	private PointRedisModel getPoint(String id, String location) {
		PointRedisModel point = new PointRedisModel();
		point.setId(id);
		point.setLocation(location);
		return point;
	}
	
	private PointsOfSaleModel getResponsePointsRedis(List<PointRedisModel> list) {
		PointsOfSaleModel points = new PointsOfSaleModel();
		points.setPointOfSales(list);
		return points;
	}
}
