package com.capo.asignacion_redis.adapter.out.persistence.startingApp;

import com.capo.asignacion_redis.adapter.out.model.PointsRedisModel;
import com.capo.asignacion_redis.adapter.out.model.VertexRedisModel;

public interface OperationsInRedisStarting {
	String saveAndUpdateCostAndDestinationStartingApp(VertexRedisModel request);
	String savePointsOfSaleStartingApp(PointsRedisModel request);
}
