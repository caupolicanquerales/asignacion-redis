package com.capo.asignacion_redis.adapter.out.operationDestination;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.capo.adapter.kafkaEvents.RedisDestinationEvent;
import com.capo.asignacion_redis.adapter.in.model.DestinationModel;
import com.capo.asignacion_redis.adapter.mappers.MapperRedisEvent;
import com.capo.asignacion_redis.adapter.out.emitEvents.EmitingEvent;

import reactor.core.publisher.Mono;
import com.capo.asignacion_redis.adapter.out.persistence.redisOperations.OperationDestination;


@Service
public class SaveDestinationImpl implements SaveDestination{
	
	private final EmitingEvent<RedisDestinationEvent> emitEvent;
	private final OperationDestination operationDestination;
	
	private static final Logger log = LoggerFactory.getLogger(SaveDestinationImpl.class);
	
	public SaveDestinationImpl(OperationDestination operationDestination,
			EmitingEvent<RedisDestinationEvent> emitEvent) {
		this.emitEvent= emitEvent;
		this.operationDestination = operationDestination;
	}
	
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
	
}
