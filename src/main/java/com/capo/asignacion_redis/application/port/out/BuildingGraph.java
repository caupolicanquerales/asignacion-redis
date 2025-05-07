package com.capo.asignacion_redis.application.port.out;

import com.capo.asignacion_redis.application.domain.model.GraphModel;

import reactor.core.publisher.Mono;

public interface BuildingGraph {
	Mono<GraphModel> buildingGraph();
}
