package com.capo.asignacion_redis.adapter.out.persistence.redisOperations;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.redisson.api.RMapReactive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capo.asignacion_redis.adapter.enums.RedisEnum;
import com.capo.asignacion_redis.adapter.in.model.DestinationModel;
import com.capo.asignacion_redis.adapter.out.model.DestinationsModel;
import com.capo.asignacion_redis.adapter.out.persistence.redisPetition.BasicPetitionToRedis;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OperationDestinationImpl implements OperationDestination{
	
	@Autowired
	BasicPetitionToRedis petitionRedis;
	
	@Override
	public Mono<DestinationsModel> getAllCostsAndDestinations() {
		RMapReactive<String,String> map = this.petitionRedis.getReactiveMap(RedisEnum.MAP_COST.value);
		return map.entryIterator().map(this::getCostAndDestination)
			.collect(Collectors.toList())
			.map(this::getResponseCostDestinations);
	}
	
	@Override
	public Flux<Entry<String, String>> getMapCost() {
		return this.petitionRedis.getReactiveMap(RedisEnum.MAP_COST.value)
		.entryIterator();
	}
	
	@Override
	public Mono<DestinationModel> saveCostAndDestination(DestinationModel destinationModel) {
		String key= destinationModel.getStartVertex()+","+destinationModel.getEndVertex();
		RMapReactive<String,String> map = this.petitionRedis.getReactiveMap(RedisEnum.MAP_COST.value);
		return Mono.just(map.get(key)).flatMap(item->item)
		.hasElement().map(element->{
			if(!element) {
				map.put(key, destinationModel.getCost()).then().subscribe();
				return Mono.just(destinationModel);
			}
			return Mono.just(new DestinationModel());
		}).flatMap(result->result);
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
