package com.capo.asignacion_redis.adapter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.capo.adapter.kafkaEvents.RedisPointOfSaleEvent;
import com.capo.asignacion_redis.adapter.out.emitEvents.EmitPointOfSaleEvent;

import reactor.core.publisher.Sinks;


@Configuration
public class EmitPointOfSaleConfig {
	
	@Bean
    public EmitPointOfSaleEvent orderEventListener(){
        var sink = Sinks.many().unicast().<RedisPointOfSaleEvent>onBackpressureBuffer();
        var flux = sink.asFlux();
        return new EmitPointOfSaleEvent(sink, flux);
    }
}
