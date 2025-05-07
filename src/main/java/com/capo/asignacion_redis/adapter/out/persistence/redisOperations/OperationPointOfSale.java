package com.capo.asignacion_redis.adapter.out.persistence.redisOperations;

import java.util.Map;

import com.capo.asignacion_redis.adapter.out.model.PointsOfSaleModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OperationPointOfSale {
	Mono<PointsOfSaleModel> getPointsOfSale();
	Flux<Map.Entry<String, String>> getMapStores();
}
