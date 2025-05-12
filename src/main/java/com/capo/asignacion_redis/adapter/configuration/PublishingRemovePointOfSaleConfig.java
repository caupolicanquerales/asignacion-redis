package com.capo.asignacion_redis.adapter.configuration;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import com.capo.adapter.kafkaEvents.RedisRemovePointOfSaleEvent;
import com.capo.asignacion_redis.adapter.out.emitEvents.EventInUse;

import reactor.core.publisher.Flux;

@Configuration
public class PublishingRemovePointOfSaleConfig {
	
	private final EventInUse<RedisRemovePointOfSaleEvent> eventPublisher;
	
	private static final String DESTINATION_HEADER = "spring.cloud.stream.sendto.destination";
    private static final String EVENTS_CHANNEL = "event-remove-point";
    
	@Autowired
	public PublishingRemovePointOfSaleConfig(EventInUse<RedisRemovePointOfSaleEvent> eventPublisher) {
		this.eventPublisher=eventPublisher;
	}
	
	@Bean
    public Supplier<Flux<Message<RedisRemovePointOfSaleEvent>>> publishingRemovePointOfSaleEvent() {
        return () -> this.eventPublisher.publish()
                                        .map(this::toMessage);
    }
	
	private Message<RedisRemovePointOfSaleEvent> toMessage(RedisRemovePointOfSaleEvent event) {
        return MessageBuilder.withPayload(event)
                             .setHeader(KafkaHeaders.KEY, "15")
                             .setHeader(DESTINATION_HEADER, EVENTS_CHANNEL)
                             .build();
    }
}
