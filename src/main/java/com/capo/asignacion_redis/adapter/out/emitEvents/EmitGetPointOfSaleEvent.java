package com.capo.asignacion_redis.adapter.out.emitEvents;

import org.springframework.beans.factory.annotation.Autowired;

import com.capo.adapter.kafkaEvents.GetInformationEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class EmitGetPointOfSaleEvent implements EmitingEvent<GetInformationEvent>,EventInUse<GetInformationEvent>{
	
	@Autowired
	private EmitEvent emitEvent;
	
	private final Sinks.Many<GetInformationEvent> sink;
	private final Flux<GetInformationEvent> flux;
	
	public EmitGetPointOfSaleEvent(Sinks.Many<GetInformationEvent> sink,Flux<GetInformationEvent> flux) {
		this.sink=sink;
		this.flux=flux;
	}
	
	@Override
    public Flux<GetInformationEvent> publish() {
        return this.flux;
    }
	
	@Override
	public void emit(GetInformationEvent event) {
		emitEvent.emitEvent(sink, event);
	}
}
