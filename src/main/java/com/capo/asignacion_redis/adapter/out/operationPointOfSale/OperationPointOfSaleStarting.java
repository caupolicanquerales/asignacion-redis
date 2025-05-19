package com.capo.asignacion_redis.adapter.out.operationPointOfSale;

import com.capo.asignacion_redis.adapter.in.model.PointRedisModel;

public interface OperationPointOfSaleStarting {
	void saveFileInformationInRedis();
	PointRedisModel savingPointOfSalesInRedis(PointRedisModel response);
}
