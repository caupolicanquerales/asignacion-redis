package com.capo.asignacion_redis.adapter.out.operationDestination;

import com.capo.asignacion_redis.adapter.in.model.DestinationModel;

import reactor.core.publisher.Mono;

public interface SaveDestination {
	Mono<String> saveDestination(DestinationModel destinationModel);
}
