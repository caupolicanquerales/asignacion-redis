package com.capo.asignacion_redis.adapter.out.emitEvents;

import org.springframework.beans.factory.annotation.Autowired;

import com.capo.adapter.kafkaEvents.RedisRemovePointOfSaleEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class EmitRemovePointOfSaleEvent implements EmitingEvent<RedisRemovePointOfSaleEvent>,EventInUse<RedisRemovePointOfSaleEvent>{
	
	@Autowired
	private EmitEvent emitEvent;
	
	private final Sinks.Many<RedisRemovePointOfSaleEvent> sink;
	private final Flux<RedisRemovePointOfSaleEvent> flux;
	
	public EmitRemovePointOfSaleEvent(Sinks.Many<RedisRemovePointOfSaleEvent> sink,Flux<RedisRemovePointOfSaleEvent> flux) {
		this.sink=sink;
		this.flux=flux;
	}
	
	@Override
    public Flux<RedisRemovePointOfSaleEvent> publish() {
        return this.flux;
    }
	
	@Override
	public void emit(RedisRemovePointOfSaleEvent event) {
		emitEvent.emitEvent(sink, event);
	}
}
