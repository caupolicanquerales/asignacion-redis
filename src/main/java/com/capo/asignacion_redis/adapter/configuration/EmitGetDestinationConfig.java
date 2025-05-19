package com.capo.asignacion_redis.adapter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.capo.adapter.kafkaEvents.GetInformationDestinationEvent;
import com.capo.asignacion_redis.adapter.out.emitEvents.EmitGetDestinationEvent;

import reactor.core.publisher.Sinks;


@Configuration
public class EmitGetDestinationConfig {
	
	@Bean
    public EmitGetDestinationEvent emitGetDestinationEventListener(){
        var sink = Sinks.many().unicast().<GetInformationDestinationEvent>onBackpressureBuffer();
        var flux = sink.asFlux();
        return new EmitGetDestinationEvent(sink, flux);
    }
}
