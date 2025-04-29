package com.capo.asignacion_redis.adapter.out.emitEvents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capo.asignacion_redis.adapter.out.events.RedisPointOfSaleEvent;

import reactor.core.publisher.Sinks;

@Service
public class EmitPointOfSaleEvent implements EventInUse<RedisPointOfSaleEvent>{
	
	private final Sinks.Many<RedisPointOfSaleEvent> sink;
	private final EmitEvent emitEvent;
	
	@Autowired
	public EmitPointOfSaleEvent(Sinks.Many<RedisPointOfSaleEvent> sink,EmitEvent emitEvent) {
		this.sink=sink;
		this.emitEvent=emitEvent;
	}

	@Override
	public void publishing(RedisPointOfSaleEvent event) {
		emitEvent.emitEvent(sink, event);
	}
}
