package com.capo.asignacion_redis.adapter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.capo.adapter.kafkaEvents.GetInformationEvent;
import com.capo.asignacion_redis.adapter.out.emitEvents.EmitGetPointOfSaleEvent;

import reactor.core.publisher.Sinks;


@Configuration
public class EmitGetPointOfSaleConfig {
	
	@Bean
    public EmitGetPointOfSaleEvent emitGetPointEventListener(){
        var sink = Sinks.many().unicast().<GetInformationEvent>onBackpressureBuffer();
        var flux = sink.asFlux();
        return new EmitGetPointOfSaleEvent(sink, flux);
    }
}
