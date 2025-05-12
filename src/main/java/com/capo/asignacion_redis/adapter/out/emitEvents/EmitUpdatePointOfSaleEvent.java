package com.capo.asignacion_redis.adapter.out.emitEvents;

import org.springframework.beans.factory.annotation.Autowired;

import com.capo.adapter.kafkaEvents.RedisUpdatePointOfSaleEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class EmitUpdatePointOfSaleEvent implements EmitingEvent<RedisUpdatePointOfSaleEvent>,EventInUse<RedisUpdatePointOfSaleEvent>{
	
	@Autowired
	private EmitEvent emitEvent;
	
	private final Sinks.Many<RedisUpdatePointOfSaleEvent> sink;
	private final Flux<RedisUpdatePointOfSaleEvent> flux;
	
	public EmitUpdatePointOfSaleEvent(Sinks.Many<RedisUpdatePointOfSaleEvent> sink,Flux<RedisUpdatePointOfSaleEvent> flux) {
		this.sink=sink;
		this.flux=flux;
	}
	
	@Override
    public Flux<RedisUpdatePointOfSaleEvent> publish() {
        return this.flux;
    }
	
	@Override
	public void emit(RedisUpdatePointOfSaleEvent event) {
		emitEvent.emitEvent(sink, event);
	}
}
