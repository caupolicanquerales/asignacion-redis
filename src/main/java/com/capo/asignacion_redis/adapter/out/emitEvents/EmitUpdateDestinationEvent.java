package com.capo.asignacion_redis.adapter.out.emitEvents;

import org.springframework.beans.factory.annotation.Autowired;

import com.capo.adapter.kafkaEvents.RedisUpdateDestinationEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class EmitUpdateDestinationEvent implements EmitingEvent<RedisUpdateDestinationEvent>,EventInUse<RedisUpdateDestinationEvent>{
	
	@Autowired
	private EmitEvent emitEvent;
	
	private final Sinks.Many<RedisUpdateDestinationEvent> sink;
	private final Flux<RedisUpdateDestinationEvent> flux;
	
	public EmitUpdateDestinationEvent(Sinks.Many<RedisUpdateDestinationEvent> sink,Flux<RedisUpdateDestinationEvent> flux) {
		this.sink=sink;
		this.flux=flux;
	}
	
	@Override
    public Flux<RedisUpdateDestinationEvent> publish() {
        return this.flux;
    }
	
	@Override
	public void emit(RedisUpdateDestinationEvent event) {
		emitEvent.emitEvent(sink, event);
	}
}
