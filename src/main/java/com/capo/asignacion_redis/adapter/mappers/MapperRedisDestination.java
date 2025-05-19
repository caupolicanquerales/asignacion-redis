package com.capo.asignacion_redis.adapter.mappers;

import java.util.Objects;

import com.capo.adapter.kafkaEvents.CostsAndRoutesFromEvent;
import com.capo.adapter.kafkaEvents.MongoResultDestinationEvent;
import com.capo.adapter.kafkaEvents.MongoResultPointsOfSaleEvent;
import com.capo.adapter.kafkaEvents.RedisDestinationEvent;
import com.capo.adapter.kafkaEvents.RedisRemoveDestinationEvent;
import com.capo.adapter.kafkaEvents.RedisUpdateDestinationEvent;
import com.capo.asignacion_redis.adapter.in.model.DestinationModel;
import com.capo.asignacion_redis.adapter.in.model.PointRedisModel;
import com.capo.asignacion_redis.adapter.out.model.VertexRedisModel;

public class MapperRedisDestination {
	
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
	
	public static RedisRemoveDestinationEvent mapperRemoveDestinationEvent(DestinationModel destinationModel) {
		RedisRemoveDestinationEvent event = new RedisRemoveDestinationEvent();
		if(Objects.nonNull(destinationModel.getCost())) {
			event.setCost(destinationModel.getCost());
			event.setEndVertex(destinationModel.getEndVertex());
			event.setStartVertex(destinationModel.getStartVertex());
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
	
	public static RedisDestinationEvent mapperDestinationEvent(VertexRedisModel vertexRedisModel) {
		RedisDestinationEvent event = new RedisDestinationEvent();
		event.setCost(vertexRedisModel.getCost());
		event.setEndVertex(vertexRedisModel.getEndVertex());
		event.setStartVertex(vertexRedisModel.getStartVertex());
		return event;
	}
	
	public static DestinationModel mapperDestinationModel(MongoResultDestinationEvent mongoResultDestination) {
		DestinationModel model = new DestinationModel();
		model.setCost(mongoResultDestination.getCost());
		model.setEndVertex(mongoResultDestination.getEndVertex());
		model.setStartVertex(mongoResultDestination.getStartVertex());
		return model;
	}
}
