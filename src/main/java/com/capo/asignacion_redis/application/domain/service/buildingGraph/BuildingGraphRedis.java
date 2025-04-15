package com.capo.asignacion_redis.application.domain.service.buildingGraph;

import com.capo.asignacion_redis.application.domain.model.GraphModel;

import reactor.core.publisher.Mono;

public interface BuildingGraphRedis {
	Mono<GraphModel> buildingGraph();
}
