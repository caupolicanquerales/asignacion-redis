package com.capo.asignacion_redis.adapter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.capo.adapter.kafkaEvents.RedisUpdateDestinationEvent;
import com.capo.asignacion_redis.adapter.out.emitEvents.EmitUpdateDestinationEvent;

import reactor.core.publisher.Sinks;


@Configuration
public class EmitUpdateDestinationConfig {
	
	@Bean
    public EmitUpdateDestinationEvent emitUpdateDestinationEventListener(){
        var sink = Sinks.many().unicast().<RedisUpdateDestinationEvent>onBackpressureBuffer();
        var flux = sink.asFlux();
        return new EmitUpdateDestinationEvent(sink, flux);
    }
}
