package com.capo.asignacion_redis.adapter.configuration;

import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.capo.asignacion_redis.adapter.out.events.RedisPointOfSaleEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Configuration
public class PointOfSalePublisherConfig {
	
	@Bean
	public Sinks.Many<RedisPointOfSaleEvent> sink(){
		return Sinks.many().unicast().<RedisPointOfSaleEvent>onBackpressureBuffer();
	}
	
	@Bean
	public Supplier<Flux<RedisPointOfSaleEvent>> publishingPointOfSaleEvent(Sinks.Many<RedisPointOfSaleEvent> publisher){
		return publisher::asFlux;
	}
}
