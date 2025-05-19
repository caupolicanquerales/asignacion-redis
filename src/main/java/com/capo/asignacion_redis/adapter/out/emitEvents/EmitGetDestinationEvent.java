package com.capo.asignacion_redis.adapter.out.emitEvents;

import org.springframework.beans.factory.annotation.Autowired;

import com.capo.adapter.kafkaEvents.GetInformationDestinationEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class EmitGetDestinationEvent implements EmitingEvent<GetInformationDestinationEvent>,EventInUse<GetInformationDestinationEvent>{
	
	@Autowired
	private EmitEvent emitEvent;
	
	private final Sinks.Many<GetInformationDestinationEvent> sink;
	private final Flux<GetInformationDestinationEvent> flux;
	
	public EmitGetDestinationEvent(Sinks.Many<GetInformationDestinationEvent> sink,Flux<GetInformationDestinationEvent> flux) {
		this.sink=sink;
		this.flux=flux;
	}
	
	@Override
    public Flux<GetInformationDestinationEvent> publish() {
        return this.flux;
    }
	
	@Override
	public void emit(GetInformationDestinationEvent event) {
		emitEvent.emitEvent(sink, event);
	}
}
