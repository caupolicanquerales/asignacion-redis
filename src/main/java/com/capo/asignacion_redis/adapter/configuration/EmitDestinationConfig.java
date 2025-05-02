package com.capo.asignacion_redis.adapter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.capo.adapter.kafkaEvents.RedisDestinationEvent;
import com.capo.asignacion_redis.adapter.out.emitEvents.EmitDestinationEvent;

import reactor.core.publisher.Sinks;


@Configuration
public class EmitDestinationConfig {
	
	@Bean
    public EmitDestinationEvent emitDestinationEventListener(){
        var sink = Sinks.many().unicast().<RedisDestinationEvent>onBackpressureBuffer();
        var flux = sink.asFlux();
        return new EmitDestinationEvent(sink, flux);
    }
}
