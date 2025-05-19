package com.capo.asignacion_redis.adapter.out.operationDestination;

import com.capo.asignacion_redis.adapter.in.model.DestinationModel;
import com.capo.asignacion_redis.adapter.out.model.VertexRedisModel;

public interface OperationDetinationStarting {
	void saveFileInformationInRedis();
	VertexRedisModel savingDestinationInRedis(DestinationModel response);
}
