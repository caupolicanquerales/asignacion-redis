package com.capo.asignacion_redis.adapter.out.emitEvents;

import org.springframework.beans.factory.annotation.Autowired;

import com.capo.adapter.kafkaEvents.RedisRemoveDestinationEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class EmitRemoveDestinationEvent implements EmitingEvent<RedisRemoveDestinationEvent>,EventInUse<RedisRemoveDestinationEvent>{
	
	@Autowired
	private EmitEvent emitEvent;
	
	private final Sinks.Many<RedisRemoveDestinationEvent> sink;
	private final Flux<RedisRemoveDestinationEvent> flux;
	
	public EmitRemoveDestinationEvent(Sinks.Many<RedisRemoveDestinationEvent> sink,Flux<RedisRemoveDestinationEvent> flux) {
		this.sink=sink;
		this.flux=flux;
	}
	
	@Override
    public Flux<RedisRemoveDestinationEvent> publish() {
        return this.flux;
    }
	
	@Override
	public void emit(RedisRemoveDestinationEvent event) {
		emitEvent.emitEvent(sink, event);
	}
}
