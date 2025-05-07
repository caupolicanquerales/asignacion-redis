package com.capo.asignacion_redis.adapter.out.persistence.redisOperations;

import java.util.Map.Entry;

import com.capo.asignacion_redis.adapter.out.model.DestinationsModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OperationDestination {
	Mono<DestinationsModel> getAllCostsAndDestinations();
	Flux<Entry<String, String>> getMapCost();
}
