package com.capo.asignacion_redis.adapter.configuration;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import com.capo.asignacion_redis.adapter.in.events.FromMongoResultEvent;
import com.capo.asignacion_redis.adapter.out.events.RedisDestinationEvent;
import com.capo.asignacion_redis.adapter.out.events.RedisPointOfSaleEvent;
import com.capo.asignacion_redis.adapter.utils.MessageConverter;

import reactor.core.publisher.Flux;


@Configuration
public class MongoEventProcessorConfig {
	
	private static final Logger log = LoggerFactory.getLogger(MongoEventProcessorConfig.class);
    
	
	@Bean
    public Function<Flux<Message<FromMongoResultEvent>>, Flux<Message<RedisDestinationEvent>>> processorResultDestination() {
        return flux -> flux.map(MessageConverter::toRecord)
                           .doOnNext(r -> log.info("get event from Mongo Destination {}", r.message()))
                           //.concatMap(r -> this.eventProcessor.process(r.message())
                          //                                    .doOnSuccess(e -> r.acknowledgement().acknowledge())
                          // )
                           .doOnNext(r -> r.acknowledgement().acknowledge())
                           .map(r-> mapDestination(r.message()))
                           .map(this::toMessageDestination);
    }
	
	
	@Bean
    public Function<Flux<Message<FromMongoResultEvent>>, Flux<Message<RedisPointOfSaleEvent>>> processorResultPointOfSale() {
        return flux -> flux.map(MessageConverter::toRecord)
                           .doOnNext(r -> log.info("get event from Mongo Point of Sale {}", r.message()))
                           //.concatMap(r -> this.eventProcessor.process(r.message())
                          //                                    .doOnSuccess(e -> r.acknowledgement().acknowledge())
                          // )
                           .doOnNext(r -> r.acknowledgement().acknowledge())
                           .map(r-> mapPoints(r.message()))
                           .map(this::toMessagePoint);
    }
	
	private RedisDestinationEvent mapDestination(FromMongoResultEvent redisDestination) {
		RedisDestinationEvent redisDestinationEvent= new RedisDestinationEvent();
		return redisDestinationEvent;
	}
	
	private RedisPointOfSaleEvent mapPoints(FromMongoResultEvent redisDestination) {
		RedisPointOfSaleEvent redisPointOfSaleEvent= new RedisPointOfSaleEvent();
		return redisPointOfSaleEvent;
	}
	
	private Message<RedisDestinationEvent> toMessageDestination(RedisDestinationEvent event) {
		return MessageBuilder.withPayload(event)
                             .setHeader(KafkaHeaders.KEY, "Empty")
                             .build();
    }
	
	private Message<RedisPointOfSaleEvent> toMessagePoint(RedisPointOfSaleEvent event) {
		return MessageBuilder.withPayload(event)
                             .setHeader(KafkaHeaders.KEY, "Empty")
                             .build();
    }

}
