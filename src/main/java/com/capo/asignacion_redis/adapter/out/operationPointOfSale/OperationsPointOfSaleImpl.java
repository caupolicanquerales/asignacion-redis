package com.capo.asignacion_redis.adapter.out.operationPointOfSale;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.capo.adapter.kafkaEvents.RedisPointOfSaleEvent;
import com.capo.adapter.kafkaEvents.RedisRemovePointOfSaleEvent;
import com.capo.adapter.kafkaEvents.RedisUpdatePointOfSaleEvent;
import com.capo.asignacion_redis.adapter.in.model.PointRedisModel;
import com.capo.asignacion_redis.adapter.mappers.MapperRedisEvent;
import com.capo.asignacion_redis.adapter.out.emitEvents.EmitingEvent;
import com.capo.asignacion_redis.adapter.out.persistence.redisOperations.OperationPointOfSaleInRedis;

import reactor.core.publisher.Mono;

@Service
public class OperationsPointOfSaleImpl implements OperationsPointOfSale {
	
	private final OperationPointOfSaleInRedis operationPointOfSale;
	private final EmitingEvent<RedisPointOfSaleEvent> emitEvent;
	private final EmitingEvent<RedisUpdatePointOfSaleEvent> emitUpdateEvent;
	private final EmitingEvent<RedisRemovePointOfSaleEvent> emitRemoveEvent;
	
	private static final Logger log = LoggerFactory.getLogger(OperationsPointOfSaleImpl.class);
	
	public OperationsPointOfSaleImpl(OperationPointOfSaleInRedis operationPointOfSale,
			EmitingEvent<RedisPointOfSaleEvent> emitEvent,
			EmitingEvent<RedisUpdatePointOfSaleEvent> emitUpdateEvent,
			EmitingEvent<RedisRemovePointOfSaleEvent> emitRemoveEvent) {
		this.operationPointOfSale= operationPointOfSale;
		this.emitEvent= emitEvent;
		this.emitUpdateEvent= emitUpdateEvent;
		this.emitRemoveEvent= emitRemoveEvent;
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
	
	@Override
	public Mono<String> udpateLocationPointOfSale(PointRedisModel pointRedisModel){
		return operationPointOfSale.updateLocationPointsOfSale(pointRedisModel)
		.map(model-> MapperRedisEvent.mapperUpdatePointOfSaleEvent(model))
		.doOnNext(r -> log.info("update location point of sale in Redis {}", Objects.nonNull(r)))
        .doOnNext(event->{
			if(Objects.nonNull(event)) {
				emitUpdateEvent.emit(event);
			}
		}).map(event-> {
			return "OK";
		});
	}
	
	@Override
	public Mono<String> removePointOfSale(PointRedisModel pointRedisModel){
		return operationPointOfSale.removePointsOfSale(pointRedisModel)
		.map(model-> MapperRedisEvent.mapperRemovePointOfSaleEvent(model))
		.doOnNext(r -> log.info("remove point of sale in Redis {}", Objects.nonNull(r)))
        .doOnNext(event->{
			if(Objects.nonNull(event)) {
				emitRemoveEvent.emit(event);
			}
		}).map(event-> {
			return "OK";
		});
	}
}
