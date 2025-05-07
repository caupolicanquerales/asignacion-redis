package com.capo.asignacion_redis.adapter.out.buildingGraph;

import reactor.core.publisher.Mono;

public interface BuildingGraphDestination {
	Mono<String> buildingGraph();
}
