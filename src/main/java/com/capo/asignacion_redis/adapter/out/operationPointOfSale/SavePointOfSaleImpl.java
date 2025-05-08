package com.capo.asignacion_redis.adapter.out.operationPointOfSale;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.capo.adapter.kafkaEvents.RedisPointOfSaleEvent;
import com.capo.asignacion_redis.adapter.in.model.PointRedisModel;
import com.capo.asignacion_redis.adapter.mappers.MapperRedisEvent;
import com.capo.asignacion_redis.adapter.out.emitEvents.EmitingEvent;
import com.capo.asignacion_redis.adapter.out.persistence.redisOperations.OperationPointOfSale;

import reactor.core.publisher.Mono;

@Service
public class SavePointOfSaleImpl implements SavePointOfSale {
	
	private final OperationPointOfSale operationPointOfSale;
	private final EmitingEvent<RedisPointOfSaleEvent> emitEvent;
	
	private static final Logger log = LoggerFactory.getLogger(SavePointOfSaleImpl.class);
	
	public SavePointOfSaleImpl(OperationPointOfSale operationPointOfSale,
			EmitingEvent<RedisPointOfSaleEvent> emitEvent) {
		this.operationPointOfSale= operationPointOfSale;
		this.emitEvent= emitEvent;
	}
	
	@Override
	public Mono<String> savePointOfSale(PointRedisModel pointRedisModel){
		return operationPointOfSale.savePointsOfSale(pointRedisModel)
		.map(model-> MapperRedisEvent.mapperPointOfSaleEvent(model))
		.doOnNext(r -> log.info("save point of sale in Redis {}", Objects.nonNull(r)))
        .doOnNext(event->{
			if(Objects.nonNull(event)) {
				emitEvent.emit(event);
			}
		}).map(event-> {
			return "OK";
		});
	}
}
