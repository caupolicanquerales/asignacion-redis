package com.capo.asignacion_redis.adapter.out.persistence.redisOperations;

import java.util.Map.Entry;

import com.capo.asignacion_redis.adapter.in.model.DestinationModel;
import com.capo.asignacion_redis.adapter.out.model.DestinationsModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OperationDestinationInRedis {
	Mono<DestinationsModel> getAllCostsAndDestinations();
	Flux<Entry<String, String>> getMapCost();
	Mono<DestinationModel> saveCostAndDestination(DestinationModel destinationModel);
	Mono<DestinationModel> updateCostInDestination(DestinationModel destinationModel);
	Mono<DestinationModel> removeCostAndDestination(DestinationModel destinationModel);
}
