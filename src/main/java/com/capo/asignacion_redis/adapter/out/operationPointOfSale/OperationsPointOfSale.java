package com.capo.asignacion_redis.adapter.out.operationPointOfSale;

import com.capo.asignacion_redis.adapter.in.model.PointRedisModel;

import reactor.core.publisher.Mono;

public interface OperationsPointOfSale {
	Mono<String> savePointOfSale(PointRedisModel pointRedisModel);
	Mono<String> udpateLocationPointOfSale(PointRedisModel pointRedisModel);
	Mono<String> removePointOfSale(PointRedisModel pointRedisModel);
}
