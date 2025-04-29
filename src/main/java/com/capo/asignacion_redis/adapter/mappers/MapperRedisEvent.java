package com.capo.asignacion_redis.adapter.mappers;

import com.capo.asignacion_redis.adapter.out.events.RedisPointOfSaleEvent;
import com.capo.asignacion_redis.adapter.out.model.PointRedisModel;

public class MapperRedisEvent {
	
	public static RedisPointOfSaleEvent mapperPointOfSaleEvent(PointRedisModel pointRedisModel) {
		RedisPointOfSaleEvent event = new RedisPointOfSaleEvent();
		event.setId(pointRedisModel.getId());
		event.setLocation(pointRedisModel.getLocation());
		return event;
	}
}
