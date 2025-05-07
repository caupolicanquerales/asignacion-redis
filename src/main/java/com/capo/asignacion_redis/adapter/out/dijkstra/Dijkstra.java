package com.capo.asignacion_redis.adapter.out.dijkstra;

import com.capo.asignacion_redis.adapter.in.model.DestinationModel;
import com.capo.asignacion_redis.adapter.out.model.ResponseGraphRedisModel;

import reactor.core.publisher.Mono;

public interface Dijkstra {
	Mono<ResponseGraphRedisModel> estimationOfCosts(DestinationModel destination);
}
