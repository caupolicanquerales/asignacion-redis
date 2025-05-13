package com.capo.asignacion_redis.adapter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.capo.adapter.kafkaEvents.RedisRemoveDestinationEvent;
import com.capo.asignacion_redis.adapter.out.emitEvents.EmitRemoveDestinationEvent;

import reactor.core.publisher.Sinks;


@Configuration
public class EmitRemoveDestinationConfig {
	
	@Bean
    public EmitRemoveDestinationEvent emitRemoveDestinationEventListener(){
        var sink = Sinks.many().unicast().<RedisRemoveDestinationEvent>onBackpressureBuffer();
        var flux = sink.asFlux();
        return new EmitRemoveDestinationEvent(sink, flux);
    }
}
