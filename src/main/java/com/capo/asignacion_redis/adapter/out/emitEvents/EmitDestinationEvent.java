package com.capo.asignacion_redis.adapter.out.emitEvents;

import org.springframework.beans.factory.annotation.Autowired;

import com.capo.adapter.kafkaEvents.RedisDestinationEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class EmitDestinationEvent implements EmitingEvent<RedisDestinationEvent>,EventInUse<RedisDestinationEvent>{
	
	@Autowired
	private EmitEvent emitEvent;
	
	private final Sinks.Many<RedisDestinationEvent> sink;
	private final Flux<RedisDestinationEvent> flux;
	
	public EmitDestinationEvent(Sinks.Many<RedisDestinationEvent> sink,Flux<RedisDestinationEvent> flux) {
		this.sink=sink;
		this.flux=flux;
	}
	
	@Override
    public Flux<RedisDestinationEvent> publish() {
        return this.flux;
    }
	
	@Override
	public void emit(RedisDestinationEvent event) {
		emitEvent.emitEvent(sink, event);
	}
}
