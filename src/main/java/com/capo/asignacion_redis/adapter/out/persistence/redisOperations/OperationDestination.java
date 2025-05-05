package com.capo.asignacion_redis.adapter.out.persistence.redisOperations;

import com.capo.asignacion_redis.adapter.out.model.DestinationsModel;

import reactor.core.publisher.Mono;

public interface OperationDestination {
	Mono<DestinationsModel> getAllCostsAndDestinations();
}
