package com.capo.asignacion_redis.adapter.out.operationDestination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.capo.adapter.kafkaEvents.RedisDestinationEvent;
import com.capo.asignacion_redis.adapter.in.file.RecoverFileFromResource;
import com.capo.asignacion_redis.adapter.in.model.DestinationModel;
import com.capo.asignacion_redis.adapter.mappers.MapperRedisDestination;
import com.capo.asignacion_redis.adapter.out.emitEvents.EmitingEvent;
import com.capo.asignacion_redis.adapter.out.model.DestinationsModel;
import com.capo.asignacion_redis.adapter.out.model.VertexRedisModel;
import com.capo.asignacion_redis.adapter.out.persistence.startingApp.OperationsInRedisStarting;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;

@Service
public class OperationDestinationStartingAppImpl implements OperationDetinationStarting{
	
	private final EmitingEvent<RedisDestinationEvent> emitEvent;
	private final OperationsInRedisStarting operationsInRedis;
	private final RecoverFileFromResource recoverFile;
	private static final String FILE_COSTS="/information/cost_and_destination_point_of_sales.json";
	private static final Logger log = LoggerFactory.getLogger(OperationDestinationStartingAppImpl.class);
	
	public OperationDestinationStartingAppImpl(EmitingEvent<RedisDestinationEvent> emitEvent,
			OperationsInRedisStarting operationsInRedis,
			RecoverFileFromResource recoverFile) {
		this.operationsInRedis=operationsInRedis;
		this.recoverFile= recoverFile;
		this.emitEvent= emitEvent;
	}
	
	@Override
	public void saveFileInformationInRedis(){
		DestinationsModel destination;
		try {
			destination = getFileFromResourceCostAndDestination(FILE_COSTS);
			Flux.fromIterable(destination.getCostAndDestination())
			.map(this::savingDestinationInRedis)
			.map(model-> MapperRedisDestination.mapperDestinationEvent(model))
			.doOnNext(event->emitEvent.emit(event))
			.subscribe();
		} catch (Exception e) {
			log.info("error in save file information destination in Redis {}", e);
		}
	}
	
	private DestinationsModel getFileFromResourceCostAndDestination(String address) throws Exception{
		String json= recoverFile.getFileInString(address);
		ObjectMapper mapper = new ObjectMapper();
		DestinationsModel destinations= mapper.readValue(json, DestinationsModel.class);
		return destinations;
	}
	
	@Override
	public VertexRedisModel savingDestinationInRedis(DestinationModel response) {
		VertexRedisModel vertexRedis= new VertexRedisModel();
		vertexRedis.setStartVertex(response.getStartVertex());
		vertexRedis.setEndVertex(response.getEndVertex());
		vertexRedis.setCost(response.getCost());
		VertexRedisModel result= operationsInRedis.saveAndUpdateCostAndDestinationStartingApp(vertexRedis);
		return result;
	}
}
