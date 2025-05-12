package com.capo.asignacion_redis.adapter.out.operationDestination;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.capo.adapter.kafkaEvents.RedisDestinationEvent;
import com.capo.adapter.kafkaEvents.RedisUpdateDestinationEvent;
import com.capo.asignacion_redis.adapter.in.model.DestinationModel;
import com.capo.asignacion_redis.adapter.mappers.MapperRedisEvent;
import com.capo.asignacion_redis.adapter.out.emitEvents.EmitingEvent;
import com.capo.asignacion_redis.adapter.out.persistence.redisOperations.OperationDestinationInRedis;

import reactor.core.publisher.Mono;


@Service
public class OperationsDestinationImpl implements OperationsDestination{
	
	private final EmitingEvent<RedisDestinationEvent> emitEvent;
	private final EmitingEvent<RedisUpdateDestinationEvent> emitUpdateEvent;
	private final OperationDestinationInRedis operationDestination;
	
	private static final Logger log = LoggerFactory.getLogger(OperationsDestinationImpl.class);
	
	public OperationsDestinationImpl(OperationDestinationInRedis operationDestination,
			EmitingEvent<RedisDestinationEvent> emitEvent,
			EmitingEvent<RedisUpdateDestinationEvent> emitUpdateEvent) {
		this.emitEvent= emitEvent;
		this.emitUpdateEvent= emitUpdateEvent;
		this.operationDestination = operationDestination;
	}
	
	@Override
	public Mono<String> saveDestination(DestinationModel destinationModel){
		return operationDestination.saveCostAndDestination(destinationModel)
		.map(model-> MapperRedisEvent.mapperDestinationEvent(model))
		.doOnNext(r -> log.info("save destination in Redis {}", Objects.nonNull(r)))
		.doOnNext(event->{
			if(Objects.nonNull(event)) {
				emitEvent.emit(event);
			}
		}).map(event-> {
			return "OK";
		});
	}
	
	@Override
	public Mono<String> updateCostInDestination(DestinationModel destinationModel){
		return operationDestination.updateCostInDestination(destinationModel)
		.map(model-> MapperRedisEvent.mapperUpdateDestinationEvent(model))
		.doOnNext(r -> log.info("update cost in destination Redis {}", Objects.nonNull(r)))
        .doOnNext(event->{
			if(Objects.nonNull(event)) {
				emitUpdateEvent.emit(event);
			}
		}).map(event-> {
			return "OK";
		});
	}
}
