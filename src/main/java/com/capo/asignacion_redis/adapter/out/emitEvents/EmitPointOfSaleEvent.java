package com.capo.asignacion_redis.adapter.out.emitEvents;

import org.springframework.beans.factory.annotation.Autowired;

import com.capo.adapter.kafkaEvents.RedisPointOfSaleEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class EmitPointOfSaleEvent implements EmitingEvent<RedisPointOfSaleEvent>,EventInUse<RedisPointOfSaleEvent>{
	
	@Autowired
	private EmitEvent emitEvent;
	
	private final Sinks.Many<RedisPointOfSaleEvent> sink;
	private final Flux<RedisPointOfSaleEvent> flux;
	
	public EmitPointOfSaleEvent(Sinks.Many<RedisPointOfSaleEvent> sink,Flux<RedisPointOfSaleEvent> flux) {
		this.sink=sink;
		this.flux=flux;
	}
	
	@Override
    public Flux<RedisPointOfSaleEvent> publish() {
        return this.flux;
    }
	
	@Override
	public void emit(RedisPointOfSaleEvent event) {
		emitEvent.emitEvent(sink, event);
	}
}
