package com.capo.asignacion_redis.adapter.out.emitEvents;

import org.springframework.beans.factory.annotation.Autowired;

import com.capo.adapter.kafkaEvents.GetPointsOfSaleEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class EmitGetPointOfSaleEvent implements EmitingEvent<GetPointsOfSaleEvent>,EventInUse<GetPointsOfSaleEvent>{
	
	@Autowired
	private EmitEvent emitEvent;
	
	private final Sinks.Many<GetPointsOfSaleEvent> sink;
	private final Flux<GetPointsOfSaleEvent> flux;
	
	public EmitGetPointOfSaleEvent(Sinks.Many<GetPointsOfSaleEvent> sink,Flux<GetPointsOfSaleEvent> flux) {
		this.sink=sink;
		this.flux=flux;
	}
	
	@Override
    public Flux<GetPointsOfSaleEvent> publish() {
        return this.flux;
    }
	
	@Override
	public void emit(GetPointsOfSaleEvent event) {
		emitEvent.emitEvent(sink, event);
	}
}
