package com.capo.asignacion_redis.adapter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.capo.adapter.kafkaEvents.RedisRemovePointOfSaleEvent;
import com.capo.asignacion_redis.adapter.out.emitEvents.EmitRemovePointOfSaleEvent;

import reactor.core.publisher.Sinks;


@Configuration
public class EmitRemovePointOfSaleConfig {
	
	@Bean
    public EmitRemovePointOfSaleEvent emitRemovePointEventListener(){
        var sink = Sinks.many().unicast().<RedisRemovePointOfSaleEvent>onBackpressureBuffer();
        var flux = sink.asFlux();
        return new EmitRemovePointOfSaleEvent(sink, flux);
    }
}
