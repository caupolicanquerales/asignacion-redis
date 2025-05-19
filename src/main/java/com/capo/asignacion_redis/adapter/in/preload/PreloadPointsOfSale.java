package com.capo.asignacion_redis.adapter.in.preload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.capo.adapter.kafkaEvents.GetInformationEvent;
import com.capo.asignacion_redis.adapter.out.emitEvents.EmitingEvent;

import reactor.core.publisher.Mono;

@Component
public class PreloadPointsOfSale implements CommandLineRunner {
	
	@Autowired
	EmitingEvent<GetInformationEvent> emitGetPointsEvent;
	
	private static final String POINTS="points";
	
	
	@Override
	public void run(String... args) throws Exception {
		for(String arg: args) {
			if(arg.equals(POINTS)) {
				Mono.just(new GetInformationEvent())
				.map(event->{
					event.setSendInformation("Enviar puntos");
					return event;
				})
				.doOnNext(event->emitGetPointsEvent.emit(event))
				.subscribe();
			}
		}
	}
	
}
