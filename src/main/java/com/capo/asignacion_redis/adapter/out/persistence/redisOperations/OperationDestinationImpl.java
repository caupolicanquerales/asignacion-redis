package com.capo.asignacion_redis.adapter.out.persistence.redisOperations;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.redisson.api.RMapReactive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capo.asignacion_redis.adapter.enums.RedisEnum;
import com.capo.asignacion_redis.adapter.in.model.DestinationModel;
import com.capo.asignacion_redis.adapter.out.model.DestinationsModel;
import com.capo.asignacion_redis.adapter.out.persistence.redisPetition.InputPetitionToRedis;

import reactor.core.publisher.Mono;

@Service
public class OperationDestinationImpl implements OperationDestination{
	
	@Autowired
	InputPetitionToRedis petitionRedis;
	
	@Override
	public Mono<DestinationsModel> getAllCostsAndDestinations() {
		RMapReactive<String,String> map = this.petitionRedis.getReactiveMap(RedisEnum.MAP_COST.value);
		return map.entryIterator().map(this::getCostAndDestination)
			.collect(Collectors.toList())
			.map(this::getResponseCostDestinations);
	}
	
	private DestinationModel getCostAndDestination(Map.Entry<String, String> entry) {
		DestinationModel destination = new DestinationModel();
		String[] vertex= entry.getKey().split(","); 
		destination.setCost(entry.getValue());
		destination.setStartVertex(vertex[0]);
		destination.setEndVertex(vertex[1]);
		return destination;
	}
	
	private DestinationsModel getResponseCostDestinations(List<DestinationModel> list) {
		DestinationsModel points = new DestinationsModel();
		points.setCostAndDestination(list);
		return points;
	}
}
