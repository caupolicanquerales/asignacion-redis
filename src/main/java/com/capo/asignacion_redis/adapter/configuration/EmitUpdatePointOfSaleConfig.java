package com.capo.asignacion_redis.adapter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.capo.adapter.kafkaEvents.RedisUpdatePointOfSaleEvent;
import com.capo.asignacion_redis.adapter.out.emitEvents.EmitUpdatePointOfSaleEvent;

import reactor.core.publisher.Sinks;


@Configuration
public class EmitUpdatePointOfSaleConfig {
	
	@Bean
    public EmitUpdatePointOfSaleEvent emitUpdatePointEventListener(){
        var sink = Sinks.many().unicast().<RedisUpdatePointOfSaleEvent>onBackpressureBuffer();
        var flux = sink.asFlux();
        return new EmitUpdatePointOfSaleEvent(sink, flux);
    }
}
