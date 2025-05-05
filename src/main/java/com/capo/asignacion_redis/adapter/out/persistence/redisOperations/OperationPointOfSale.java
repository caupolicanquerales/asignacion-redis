package com.capo.asignacion_redis.adapter.out.persistence.redisOperations;

import com.capo.asignacion_redis.adapter.out.model.PointsOfSaleModel;

import reactor.core.publisher.Mono;

public interface OperationPointOfSale {
	public Mono<PointsOfSaleModel> getPointsOfSale();
}
