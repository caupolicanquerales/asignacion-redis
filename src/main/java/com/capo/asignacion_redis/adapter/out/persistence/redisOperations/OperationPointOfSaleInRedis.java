package com.capo.asignacion_redis.adapter.out.persistence.redisOperations;

import java.util.Map;

import com.capo.asignacion_redis.adapter.in.model.PointRedisModel;
import com.capo.asignacion_redis.adapter.out.model.PointsOfSaleModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OperationPointOfSaleInRedis {
	Mono<PointsOfSaleModel> getPointsOfSale();
	Flux<Map.Entry<String, String>> getMapStores();
	Mono<PointRedisModel> savePointsOfSale(PointRedisModel pointRedisModel);
	Mono<PointRedisModel> updateLocationPointsOfSale(PointRedisModel pointRedisModel);
	Mono<PointRedisModel> removePointsOfSale(PointRedisModel pointRedisModel);
}
