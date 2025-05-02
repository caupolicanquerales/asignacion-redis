package com.capo.asignacion_redis.adapter.configuration;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import com.capo.adapter.kafkaEvents.MongoResultEvent;
import com.capo.asignacion_redis.adapter.utils.MessageConverter;

import reactor.core.publisher.Flux;


@Configuration
public class ConsumingMongoEventConfig {
	
	private static final Logger log = LoggerFactory.getLogger(ConsumingMongoEventConfig.class);
    
	
	@Bean
    public Consumer<Flux<Message<MongoResultEvent>>> processorResultDestination() {
        return flux -> flux.map(MessageConverter::toRecord)
                           .doOnNext(r -> log.info("get event from Mongo Destination {}", r.message()))
                           .subscribe();
                           //.concatMap(r -> this.eventProcessor.process(r.message())
                          //                                    .doOnSuccess(e -> r.acknowledgement().acknowledge())
                          // )
                           //.doOnNext(r -> r.acknowledgement().acknowledge())
                           //.map(r-> mapDestination(r.message()))
                           //.map(this::toMessageDestination);
    }
	
	
	@Bean
    public Consumer<Flux<Message<MongoResultEvent>>> processorResultPointOfSale() {
        return flux -> flux.map(MessageConverter::toRecord)
                           .doOnNext(r -> log.info("get event from Mongo Point of Sale {}", r.message()))
                           .subscribe();
                           //.concatMap(r -> this.eventProcessor.process(r.message())
                          //                                    .doOnSuccess(e -> r.acknowledgement().acknowledge())
                          // )
                          // .doOnNext(r -> r.acknowledgement().acknowledge());
                          // .map(r-> mapPoints(r.message()))
                          // .map(this::toMessagePoint);
    }

}
