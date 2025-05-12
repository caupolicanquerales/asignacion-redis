package com.capo.asignacion_redis.adapter.mappers;

import java.util.Objects;

import com.capo.adapter.kafkaEvents.CostsAndRoutesFromEvent;
import com.capo.adapter.kafkaEvents.RedisDestinationEvent;
import com.capo.adapter.kafkaEvents.RedisPointOfSaleEvent;
import com.capo.adapter.kafkaEvents.RedisRemovePointOfSaleEvent;
import com.capo.adapter.kafkaEvents.RedisUpdateDestinationEvent;
import com.capo.adapter.kafkaEvents.RedisUpdatePointOfSaleEvent;
import com.capo.asignacion_redis.adapter.in.model.DestinationModel;
import com.capo.asignacion_redis.adapter.in.model.PointRedisModel;
import com.capo.asignacion_redis.adapter.out.model.VertexRedisModel;

public class MapperRedisEvent {
	
	public static RedisPointOfSaleEvent mapperPointOfSaleEvent(PointRedisModel pointRedisModel) {
		RedisPointOfSaleEvent event = new RedisPointOfSaleEvent();
		if(Objects.nonNull(pointRedisModel.getId())) {
			event.setId(pointRedisModel.getId());
			event.setLocation(pointRedisModel.getLocation());
			return event;
		}
		return null;
	}
	
	public static RedisDestinationEvent mapperDestinationEvent(VertexRedisModel vertexRedisModel) {
		RedisDestinationEvent event = new RedisDestinationEvent();
		event.setCost(vertexRedisModel.getCost());
		event.setEndVertex(vertexRedisModel.getEndVertex());
		event.setStartVertex(vertexRedisModel.getStartVertex());
		return event;
	}
	
	public static RedisDestinationEvent mapperDestinationEvent(DestinationModel destinationModel) {
		RedisDestinationEvent event = new RedisDestinationEvent();
		if(Objects.nonNull(destinationModel.getCost())) {
			event.setCost(destinationModel.getCost());
			event.setEndVertex(destinationModel.getEndVertex());
			event.setStartVertex(destinationModel.getStartVertex());
			return event;
		}
		return null;
	}
	
	public static RedisUpdateDestinationEvent mapperUpdateDestinationEvent(DestinationModel destinationModel) {
		RedisUpdateDestinationEvent event = new RedisUpdateDestinationEvent();
		if(Objects.nonNull(destinationModel.getCost())) {
			event.setCost(destinationModel.getCost());
			event.setEndVertex(destinationModel.getEndVertex());
			event.setStartVertex(destinationModel.getStartVertex());
			return event;
		}
		return null;
	}
	
	public static RedisUpdatePointOfSaleEvent mapperUpdatePointOfSaleEvent(PointRedisModel pointRedisModel) {
		RedisUpdatePointOfSaleEvent event = new RedisUpdatePointOfSaleEvent();
		if(Objects.nonNull(pointRedisModel.getId())) {
			event.setId(pointRedisModel.getId());
			event.setLocation(pointRedisModel.getLocation());
			return event;
		}
		return null;
	}
	
	public static RedisRemovePointOfSaleEvent mapperRemovePointOfSaleEvent(PointRedisModel pointRedisModel) {
		RedisRemovePointOfSaleEvent event = new RedisRemovePointOfSaleEvent();
		if(Objects.nonNull(pointRedisModel.getId())) {
			event.setId(pointRedisModel.getId());
			event.setLocation(pointRedisModel.getLocation());
			return event;
		}
		return null;
	}
	
	public static DestinationModel mapperToDestinationModel(CostsAndRoutesFromEvent costsAndRoutesFromEvent) {
		DestinationModel event = new DestinationModel();
		event.setEndVertex(costsAndRoutesFromEvent.getTravelTo());
		event.setStartVertex(costsAndRoutesFromEvent.getTravelfrom());
		return event;
	}
}
