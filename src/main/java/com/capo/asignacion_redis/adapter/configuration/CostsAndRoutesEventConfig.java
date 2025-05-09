package com.capo.asignacion_redis.adapter.configuration;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import com.capo.adapter.kafkaEvents.CostsAndRoutesFromEvent;
import com.capo.adapter.kafkaEvents.CostsAndRoutesFromResultEvent;
import com.capo.asignacion_redis.adapter.mappers.MapperRedisEvent;
import com.capo.asignacion_redis.adapter.out.dijkstra.Dijkstra;
import com.capo.asignacion_redis.adapter.utils.MessageConverter;

import reactor.core.publisher.Flux;

@Configuration
public class CostsAndRoutesEventConfig {
	
	private final Dijkstra dijkstra;
	private static final Logger log = LoggerFactory.getLogger(CostsAndRoutesEventConfig.class);
	
	public CostsAndRoutesEventConfig(Dijkstra dijkstra) {
		this.dijkstra=dijkstra;
	}
	
	@Bean
	public Function<Flux<Message<CostsAndRoutesFromEvent>>, Flux<Message<CostsAndRoutesFromResultEvent>>> processorCostsAndRoutes(){
		return flux-> flux.map(MessageConverter::toRecord)
				.doOnNext(r -> log.info("get event from Mongo Accreditation {}", r.message()))
				//.doOnNext(r -> r.acknowledgement().acknowledge())
				.map(r-> MapperRedisEvent.mapperToDestinationModel(r.message()))
				.map(dijkstra::getCostsAndRoutesToAccreditation)
				.concatMap(result->result)
				.map(result->toMessageDestination(result));
	}
	

	private Message<CostsAndRoutesFromResultEvent> toMessageDestination(CostsAndRoutesFromResultEvent event) {
		return MessageBuilder.withPayload(event)
                             .setHeader(KafkaHeaders.KEY, "10")
                             .build();
    }
}
