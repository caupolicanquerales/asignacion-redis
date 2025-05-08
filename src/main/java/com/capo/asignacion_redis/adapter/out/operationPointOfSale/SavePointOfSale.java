package com.capo.asignacion_redis.adapter.out.operationPointOfSale;

import com.capo.asignacion_redis.adapter.in.model.PointRedisModel;

import reactor.core.publisher.Mono;

public interface SavePointOfSale {
	Mono<String> savePointOfSale(PointRedisModel pointRedisModel);
}
