package com.capo.asignacion_redis.adapter.out.operationDestination;

import com.capo.asignacion_redis.adapter.in.model.DestinationModel;

import reactor.core.publisher.Mono;

public interface OperationsDestination {
	Mono<String> saveDestination(DestinationModel destinationModel);
	Mono<String> updateCostInDestination(DestinationModel destinationModel);
	Mono<String> removeDestination(DestinationModel destinationModel);
}
