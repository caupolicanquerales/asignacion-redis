package com.capo.asignacion_redis.adapter.out.persistence.startingApp;

import com.capo.asignacion_redis.adapter.out.model.PointRedisModel;
import com.capo.asignacion_redis.adapter.out.model.VertexRedisModel;

public interface OperationsInRedisStarting {
	String saveAndUpdateCostAndDestinationStartingApp(VertexRedisModel request);
	String savePointsOfSaleStartingApp(PointRedisModel request);
}
