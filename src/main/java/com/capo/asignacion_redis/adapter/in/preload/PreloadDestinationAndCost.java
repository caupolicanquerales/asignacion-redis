package com.capo.asignacion_redis.adapter.in.preload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.capo.adapter.kafkaEvents.GetInformationDestinationEvent;
import com.capo.asignacion_redis.adapter.out.emitEvents.EmitingEvent;

import reactor.core.publisher.Mono;

@Component
public class PreloadDestinationAndCost implements CommandLineRunner{
	
	@Autowired
	EmitingEvent<GetInformationDestinationEvent> emitGetDestinationEvent;
	
	private static final String DESTINATIONS="destinations";
	
	@Override
	public void run(String... args) throws Exception {
		for(String arg: args) {
			if(arg.equals(DESTINATIONS)) {
				Mono.just(new GetInformationDestinationEvent())
				.map(event->{
					event.setSendInformation("Enviar destinos");
					return event;
				})
				.doOnNext(event->emitGetDestinationEvent.emit(event))
				.subscribe();
			}
		}
	}
	
}
