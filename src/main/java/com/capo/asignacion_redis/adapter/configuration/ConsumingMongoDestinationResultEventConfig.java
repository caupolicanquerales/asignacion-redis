package com.capo.asignacion_redis.adapter.configuration;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import com.capo.adapter.kafkaEvents.MongoResultDestinationEvent;
import com.capo.asignacion_redis.adapter.mappers.MapperRedisDestination;
import com.capo.asignacion_redis.adapter.out.operationDestination.OperationDetinationStarting;
import com.capo.asignacion_redis.adapter.utils.MessageConverter;

import reactor.core.publisher.Flux;


@Configuration
public class ConsumingMongoDestinationResultEventConfig {
	
	private final OperationDetinationStarting operationDetinationStarting;
	private static final Logger log = LoggerFactory.getLogger(ConsumingMongoDestinationResultEventConfig.class);
	private static final String EMPTY_RESULT= "EMPTY_RESULT";
	
	public ConsumingMongoDestinationResultEventConfig(OperationDetinationStarting operationDetinationStarting) {
		this.operationDetinationStarting=operationDetinationStarting;
	}
	
	@Bean
    public Consumer<Flux<Message<MongoResultDestinationEvent>>> processorResultGetDestination() {
        return flux -> flux.map(MessageConverter::toRecord)
                           .doOnNext(r -> log.info("get event from Mongo Result get all destination {}", r.message()))
                           .map(msg-> msg.message())
                           .doOnNext(event->{
                        	   if(event.getId().equals(EMPTY_RESULT)) {
                        		   operationDetinationStarting.saveFileInformationInRedis();
                        	   }
                           }).filter(event-> !event.getId().equals(EMPTY_RESULT))
                           .map(event->MapperRedisDestination.mapperDestinationModel(event))
                           .doOnNext(model->operationDetinationStarting.savingDestinationInRedis(model))
                           .subscribe();
                           //.doOnNext(r -> r.acknowledgement().acknowledge())
                         
    }
}
