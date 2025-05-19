package com.capo.asignacion_redis.adapter.configuration;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import com.capo.adapter.kafkaEvents.MongoResultPointsOfSaleEvent;
import com.capo.asignacion_redis.adapter.mappers.MapperRedisPointOfSale;
import com.capo.asignacion_redis.adapter.out.operationPointOfSale.OperationPointOfSaleStarting;
import com.capo.asignacion_redis.adapter.utils.MessageConverter;

import reactor.core.publisher.Flux;


@Configuration
public class ConsumingMongoPointsResultEventConfig {
	
	private final OperationPointOfSaleStarting operationPointOfSaleStarting;
	private static final Logger log = LoggerFactory.getLogger(ConsumingMongoPointsResultEventConfig.class);
	private static final String EMPTY_RESULT= "EMPTY_RESULT";
	
	public ConsumingMongoPointsResultEventConfig(OperationPointOfSaleStarting operationPointOfSaleStarting) {
		this.operationPointOfSaleStarting=operationPointOfSaleStarting;
	}
	
	@Bean
    public Consumer<Flux<Message<MongoResultPointsOfSaleEvent>>> processorResultGetAllPointsOfSale() {
        return flux -> flux.map(MessageConverter::toRecord)
                           .doOnNext(r -> log.info("get event from Mongo Result get all points {}", r.message()))
                           .map(msg-> msg.message())
                           .doOnNext(event->{
                        	   if(event.getId().equals(EMPTY_RESULT)) {
                        		   operationPointOfSaleStarting.saveFileInformationInRedis();
                        	   }
                           }).filter(event-> !event.getId().equals(EMPTY_RESULT))
                           .map(event->MapperRedisPointOfSale.mapperPointRedisModel(event))
                           .doOnNext(model->operationPointOfSaleStarting.savingPointOfSalesInRedis(model))
                           .subscribe();
                           //.doOnNext(r -> r.acknowledgement().acknowledge())
                         
    }
}
